package leejuyeon.pacemaker.OAuth;

import java.util.Map;
import java.util.Optional;
import leejuyeon.pacemaker.entity.User;
import leejuyeon.pacemaker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  // 시큐리티는 기본적으로 DefaultOAuth2UserService 에서 OAuth 로그인한 사용자의 정보를 가져온다.
  // 위 클래스를 확장하면, 사용자 정보로 여러 처리를 할 수 있다.
  // Ex. 사용자 정보 보완, 권한 설정, 추가 회원가입 로그인 로직 구현, 기존 사용자와 연동, 사용자 로깅 등

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    // super.loadUser(userRequest) 의 형태
    // Name: [카카오숫자아이디],
    // Granted Authorities: [[OAUTH2_USER, SCOPE_account_email, SCOPE_profile_nickname]], -> 카카오 디벨롭에서 동의항목 설정한 것들
    // User Attributes: [{   id=카카오숫자아이디, ...
    //                       kakao_account={ ...   profile={ nickname=사용자 닉네임(기본값이 사용자 이름인듯) },
    //                                       ...   email=사용자 이메일(카카오톡 로그인하는 이메일 아이디)
    //                                     }   }]

    Map<String, Object> kakaoAccount = super.loadUser(userRequest).getAttribute("kakao_account");
    String email = (String) kakaoAccount.get("email");

    Optional<User> user = userRepository.findByEmail(email);

    // 존재하는 User 는 그대로 반환 -> 로그인
    if (user.isPresent()) {
      return new UserPrincipal(user.get());
    } else {
      Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
      String nickname = (String) profile.get("nickname");

      // 없는 User 는 새로 저장 후 반환 -> 회원가입 및 로그인
      return new UserPrincipal(userRepository.save(User.builder()
                                                        .email(email)
                                                        .nickname(nickname)
                                                        .build()));
    }
  }

}

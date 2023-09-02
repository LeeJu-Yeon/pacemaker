package leejuyeon.pacemaker.controller;

import jakarta.validation.Valid;
import leejuyeon.pacemaker.OAuth.UserPrincipal;
import leejuyeon.pacemaker.dto.UserDto;
import leejuyeon.pacemaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  // 시큐리티 OAuth 2.0 을 설정하면, 기본적인 로그인페이지 주소는 http://localhost:8080/login 이다.
  // 위 로그인페이지에 접속하면, application.yml 에서 oauth2 client 로 등록한 kakao, naver 등의 목록이 보인다.
  // kakao 클릭시 http://localhost:8080/oauth2/authorization/kakao 로 연결되고, 카카오 로그인이 진행된다.
  // /oauth2/authorization/kakao 주소는, oauth2 client 라이브러리 사용시 고정되는 주소형이다.
  // 위 과정은 SecurityConfiguration 을 아예 작성 안했을 경우,
  // 혹은 작성했을시 securityFilterChain 에 .oauth2Login() 설정을 포함해야만 동작한다.

  // User 로그인
  // 위와 같이 시큐리티에서 자동으로 로그인페이지를 생성하기에 따로 작성하지 않아도 된다.
  // 로그인페이지 주소를 바꾸고 싶은 경우,
  // application.yml 에서 oauth2 login-url 에 원하는 주소를 지정하거나
  // SecurityConfiguration 에서 .oauth2Login().loginPage() 에 원하는 주소를 작성하면 된다.

  // User 정보 수정
  @PutMapping("/info")
  public ResponseEntity<String> updateUserInfo(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                               @RequestBody @Valid UserDto.Update update) {
    return ResponseEntity.ok(userService.updateUserInfo(userPrincipal, update));
  }

}

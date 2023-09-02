package leejuyeon.pacemaker.service;

import leejuyeon.pacemaker.OAuth.UserPrincipal;
import leejuyeon.pacemaker.dto.UserDto;
import leejuyeon.pacemaker.entity.User;
import leejuyeon.pacemaker.enums.Error;
import leejuyeon.pacemaker.exception.UserException;
import leejuyeon.pacemaker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public String updateUserInfo(UserPrincipal userPrincipal, UserDto.Update update) {

    User user = userRepository.findById(userPrincipal.getUser().getId())
        .orElseThrow(() -> new UserException(Error.USER_NOT_FOUND));

    user.setNickname(update.getNickname());
    user.setWeight(update.getWeight());
    user.setMuscleMass(update.getMuscleMass());
    user.setFatMass(update.getFatMass());
    user.setVisibility(update.getVisibility());

    userRepository.save(user);

    return "사용자 정보 수정 완료";
  }

}

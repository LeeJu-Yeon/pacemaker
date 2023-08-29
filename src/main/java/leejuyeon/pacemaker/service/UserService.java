package leejuyeon.pacemaker.service;

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

    public String updateUserInfo(User user, UserDto.Info info) {

        User foundUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserException(Error.USER_NOT_FOUND));

        foundUser.setNickname(info.getNickname());
        foundUser.setWeight(info.getWeight());
        foundUser.setMuscleMass(info.getMuscleMass());
        foundUser.setFatMass(info.getFatMass());
        foundUser.setVisibility(info.getVisibility());

        return "사용자 정보 수정 완료";
    }

}

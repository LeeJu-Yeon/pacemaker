package leejuyeon.pacemaker.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Error {

    USER_NOT_FOUND("해당 아이디의 사용자가 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_TOKEN("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus httpStatus;

}

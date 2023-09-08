package leejuyeon.pacemaker.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Error {

  USER_NOT_FOUND("해당 아이디의 사용자가 없습니다.", HttpStatus.NOT_FOUND),
  PARTY_NOT_FOUND("해당 아이디의 파티가 없습니다.", HttpStatus.NOT_FOUND),
  PARTY_FULL("파티의 모집 인원이 다 찼습니다.", HttpStatus.BAD_REQUEST),
  PARTY_JOIN_LOCK("다른 사용자가 해당 파티에 참가신청을 하고 있습니다.", HttpStatus.CONFLICT);

  private final String message;
  private final HttpStatus httpStatus;

}

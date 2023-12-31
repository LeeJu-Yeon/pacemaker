package leejuyeon.pacemaker.exception;

import leejuyeon.pacemaker.enums.Error;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {

  private final Error error;
  private final String message;
  private final HttpStatus httpStatus;

  public UserException(Error error) {
    this.error = error;
    this.message = error.getMessage();
    this.httpStatus = error.getHttpStatus();
  }

}

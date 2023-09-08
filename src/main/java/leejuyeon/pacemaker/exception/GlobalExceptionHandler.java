package leejuyeon.pacemaker.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserException.class)
  public ResponseEntity<String> handleUserException(UserException e) {
    log.error("UserException", e);
    return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
  }

  @ExceptionHandler(PartyException.class)
  public ResponseEntity<String> handlePartyException(PartyException e) {
    log.error("PartyException", e);
    return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception e) {
    log.error("Exception", e);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
  }

}

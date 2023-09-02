package leejuyeon.pacemaker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/party")
public class PartyController {

  // 파티 생성
  @PostMapping
  public ResponseEntity<?> addParty() {
    return ResponseEntity.ok("");
  }

  // 파티 검색 - 파라미터 : 날짜 - 디폴트 : 당일날짜 - 정렬 : 시간순(오름)
  @GetMapping("/search")
  public ResponseEntity<?> searchParties() {
    return ResponseEntity.ok("");
  }

  // 파티 참가
  @PostMapping("/{partyId}/join")
  public ResponseEntity<?> joinParty() {
    return ResponseEntity.ok("");
  }

}

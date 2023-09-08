package leejuyeon.pacemaker.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.List;
import leejuyeon.pacemaker.OAuth.UserPrincipal;
import leejuyeon.pacemaker.dto.PartyDto;
import leejuyeon.pacemaker.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/party")
public class PartyController {

  private final PartyService partyService;

  // 파티 생성
  @PostMapping
  public ResponseEntity<String> addParty(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                         @RequestBody @Valid PartyDto.Add add) {
    return ResponseEntity.ok(partyService.addParty(userPrincipal, add));
  }

  // 파티 검색 - 파라미터 : 날짜(디폴트 : 당일) - 정렬 : 시간순(오름)
  @GetMapping("/search")
  public ResponseEntity<List<PartyDto.Details>> searchParties(@RequestParam(required = false)
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                              @FutureOrPresent LocalDate date) {
    if (date == null) {
      date = LocalDate.now();
    }
    return ResponseEntity.ok(partyService.searchParties(date));
  }

  // 파티 참가
  @PostMapping("/{partyId}/join")
  public ResponseEntity<String> joinParty(@PathVariable Long partyId,
                                          @AuthenticationPrincipal UserPrincipal userPrincipal) {
    return ResponseEntity.ok(partyService.joinParty(partyId, userPrincipal));
  }

}

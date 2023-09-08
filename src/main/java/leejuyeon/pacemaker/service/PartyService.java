package leejuyeon.pacemaker.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import leejuyeon.pacemaker.OAuth.UserPrincipal;
import leejuyeon.pacemaker.dto.PartyDto;
import leejuyeon.pacemaker.entity.Participant;
import leejuyeon.pacemaker.entity.Party;
import leejuyeon.pacemaker.entity.User;
import leejuyeon.pacemaker.enums.Error;
import leejuyeon.pacemaker.exception.PartyException;
import leejuyeon.pacemaker.exception.UserException;
import leejuyeon.pacemaker.redissonLock.PartyLock;
import leejuyeon.pacemaker.repository.ParticipantRepository;
import leejuyeon.pacemaker.repository.PartyRepository;
import leejuyeon.pacemaker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartyService {

  private final UserRepository userRepository;
  private final PartyRepository partyRepository;
  private final ParticipantRepository participantRepository;

  // 파티 생성
  public String addParty(UserPrincipal userPrincipal, PartyDto.Add add) {

    User user = userRepository.findById(userPrincipal.getUser().getId())
        .orElseThrow(() -> new UserException(Error.USER_NOT_FOUND));

    partyRepository.save(Party.builder()
        .user(user)
        .shortIntro(add.getShortIntro())
        .exerciseDate(add.getExerciseDate())
        .startTime(add.getStartTime())
        .durationHours(add.getDurationHours())
        .maxParticipants(add.getMaxParticipants())
        .build());

    return "파티 생성 완료";
  }

  // 파티 검색 - 파라미터 : 날짜(디폴트 : 당일) - 정렬 : 시간순(오름)
  public List<PartyDto.Details> searchParties(LocalDate date) {

    List<PartyDto.Details> result = new ArrayList<>();

    List<Party> partyList;

    // 당일이면 운동시작시간이 현재로부터 1시간 이후인 파티만 검색
    if (date.equals(LocalDate.now())) {
      LocalTime time = LocalTime.now().plusHours(1);
      partyList = partyRepository
          .findAllByExerciseDateAndStartTimeAfterOrderByStartTimeAsc(date, time);
    } else {
      partyList = partyRepository.findAllByExerciseDateOrderByStartTimeAsc(date);
    }

    // 파티의 현재인원 카운트 -> 자리 여유 있는 파티만 result 에 추가
    for (Party party : partyList) {
      int currentParticipants = participantRepository.countByParty(party);

      if (currentParticipants < party.getMaxParticipants()) {
        result.add(PartyDto.Details.builder()
            .id(party.getId())
            .shortIntro(party.getShortIntro())
            .exerciseDate(party.getExerciseDate())
            .startTime(party.getStartTime())
            .durationHours(party.getDurationHours())
            .currentParticipants(currentParticipants)
            .maxParticipants(party.getMaxParticipants())
            .build());
      }
    }

    return result;
  }

  // 파티 참가 -> 동시성 이슈
  @PartyLock
  public String joinParty(Long partyId, UserPrincipal userPrincipal) {

    // 파티 유무 체크
    Party party = partyRepository.findById(partyId)
        .orElseThrow(() -> new PartyException(Error.PARTY_NOT_FOUND));

    // 유저 유무 체크
    User user = userRepository.findById(userPrincipal.getUser().getId())
        .orElseThrow(() -> new UserException(Error.USER_NOT_FOUND));

    // 파티의 현재인원 카운트
    int currentParticipants = participantRepository.countByParty(party);

    // 최대인원 초과 체크
    if (currentParticipants >= party.getMaxParticipants()) {
      throw new PartyException(Error.PARTY_FULL);
    }

    participantRepository.save(Participant.builder()
        .party(party)
        .user(user)
        .build());

    return "파티 참가 성공";
  }

}

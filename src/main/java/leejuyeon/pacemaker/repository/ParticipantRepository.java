package leejuyeon.pacemaker.repository;

import leejuyeon.pacemaker.entity.Participant;
import leejuyeon.pacemaker.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

  int countByParty(Party party);

}

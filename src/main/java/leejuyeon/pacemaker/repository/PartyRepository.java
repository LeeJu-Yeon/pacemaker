package leejuyeon.pacemaker.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import leejuyeon.pacemaker.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

  List<Party> findAllByExerciseDateOrderByStartTimeAsc(LocalDate date);

  List<Party> findAllByExerciseDateAndStartTimeAfterOrderByStartTimeAsc(LocalDate date, LocalTime time);

}

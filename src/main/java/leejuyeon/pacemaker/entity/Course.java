package leejuyeon.pacemaker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import leejuyeon.pacemaker.enums.ExerciseBodyPart;
import leejuyeon.pacemaker.enums.ExerciseGoal;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private User user;

  private String name;

  private int totalWeeks;

  private int exerciseCount;

  @Enumerated(EnumType.STRING)
  private ExerciseBodyPart exerciseBodyPart;

  @Enumerated(EnumType.STRING)
  private ExerciseGoal goal;

  @Lob
  private String content;

  private Long challengersCount;

  private Long likesCount;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

}

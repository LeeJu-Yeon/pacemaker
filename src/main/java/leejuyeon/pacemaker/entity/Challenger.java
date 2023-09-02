package leejuyeon.pacemaker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Challenger {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Course course;

  @ManyToOne
  private User user;

  private int completedDays;

  private boolean completedStatus;

  private boolean likeStatus;

}

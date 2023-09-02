package leejuyeon.pacemaker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import leejuyeon.pacemaker.enums.ImageCategory;

@Entity
public class PostImage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Post post;

  @ManyToOne
  private Image image;

  @Enumerated(EnumType.STRING)
  private ImageCategory category;

}

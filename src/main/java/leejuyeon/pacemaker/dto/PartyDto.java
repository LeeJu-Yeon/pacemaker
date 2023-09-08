package leejuyeon.pacemaker.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class PartyDto {

  @Getter
  @NotNull
  @AllArgsConstructor
  public static class Add {

    private String shortIntro;
    @Future
    private LocalDate exerciseDate;
    private LocalTime startTime;
    @Min(1)
    @Max(6)
    private int durationHours;
    @Min(2)
    @Max(4)
    private int maxParticipants;
  }

  @Builder
  public static class Details {

    private Long id;
    private String shortIntro;
    private LocalDate exerciseDate;
    private LocalTime startTime;
    private int durationHours;
    private int currentParticipants;
    private int maxParticipants;
  }

}

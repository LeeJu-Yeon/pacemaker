package leejuyeon.pacemaker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserDto {

  @Getter
  @AllArgsConstructor
  public static class Update {

    @NotNull
    private String nickname;
    private Float weight;
    private Float muscleMass;
    private Float fatMass;
    private Boolean visibility;
  }

}

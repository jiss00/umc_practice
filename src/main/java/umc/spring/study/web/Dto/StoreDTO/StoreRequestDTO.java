package umc.spring.study.web.Dto.StoreDTO;

import lombok.Getter;
import umc.spring.study.validation.annotation.ExistMission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class StoreRequestDTO {

    @Getter
    public static class ReveiwDTO{
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;
    }
    @Getter
    public static class MissionDTO{
        @NotNull
        Integer reward;
        @NotNull
        LocalDate deadline;
        @NotBlank
        String missionspec;
    }
    @Getter
    public static class ChallengingDTO{
        @ExistMission
        Long id;

    }
}
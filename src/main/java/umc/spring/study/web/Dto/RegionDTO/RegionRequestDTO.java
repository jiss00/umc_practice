package umc.spring.study.web.Dto.RegionDTO;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegionRequestDTO {
    @Getter
    public static class RegionDTO{
        @NotBlank
        String name;
    }
}

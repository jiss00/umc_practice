package umc.spring.study.web.Dto.StoreDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreSaveReponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStoreResultDTO{
        String name;
        String address;
        Float score;
    }
}

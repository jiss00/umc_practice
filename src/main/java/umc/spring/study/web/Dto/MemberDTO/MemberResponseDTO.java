package umc.spring.study.web.Dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor //기본 생성자 생성 시키는 어노테이션(lombok 기반)
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        LocalDateTime createdAt;
    }
}

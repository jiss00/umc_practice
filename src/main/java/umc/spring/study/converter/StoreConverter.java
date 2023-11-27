package umc.spring.study.converter;

import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.web.Dto.StoreDTO.StoreRequestDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreResponseDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreSaveReponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static Review toReview(StoreRequestDTO.ReveiwDTO request){
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Store saveStore(StoreSaveReponseDTO.CreateStoreResultDTO request){
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .build();
    }
    public static Mission saveMission(StoreRequestDTO.MissionDTO request){
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionspec())
                .build();
    }
}
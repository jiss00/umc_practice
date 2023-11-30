package umc.spring.study.converter;

import org.springframework.data.domain.Page;
import umc.spring.study.domain.Enum.MissionStatus;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.web.Dto.StoreDTO.StoreRequestDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreResponseDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreSaveReponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .memberMissionList(new ArrayList<>())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.MissionPreviewDTO missionPreviewDTO(Mission mission){
        return StoreResponseDTO.MissionPreviewDTO.builder()
                .missionspec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .reward(mission.getReward())
                .build();
    }
    public static StoreResponseDTO.MissionPreviewListDTO missionPreviewListDTO(Page<Mission> missionList){
        List<StoreResponseDTO.MissionPreviewDTO> missionPreviewDTO = missionList.stream()
                .map(StoreConverter::missionPreviewDTO).collect(Collectors.toList());
        return StoreResponseDTO.MissionPreviewListDTO.builder()
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreviewDTO.size())
                .missionList(missionPreviewDTO)
                .build();
    }

    public static StoreResponseDTO.MemberMissionPreviewDTO memberMissionPreviewDTO(MemberMission memberMission){
        return StoreResponseDTO.MemberMissionPreviewDTO.builder()
                .missionStatus(MissionStatus.CHALLENGING)
                .build();
    }

    public static StoreResponseDTO.MemberMissionPreviewListDTO memberMissionPreviewListDTO(Page<MemberMission> missionList){
        List<StoreResponseDTO.MemberMissionPreviewDTO> memberMissionPreviewDTO = missionList.stream()
                .map(StoreConverter::memberMissionPreviewDTO).collect(Collectors.toList());
        return StoreResponseDTO.MemberMissionPreviewListDTO.builder()
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(memberMissionPreviewDTO.size())
                .missionList(memberMissionPreviewDTO)
                .build();
    }
}
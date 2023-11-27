package umc.spring.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.*;
import umc.spring.study.repository.MemberRepository;
import umc.spring.study.service.MemberService.MemberCommandServiceImpl;
import umc.spring.study.service.MissionService.MissionService;
import umc.spring.study.service.RegionService.RegionService;
import umc.spring.study.service.StoreService.StoreCommandService;
import umc.spring.study.service.StoreService.StoreQueryServiceImpl;
import umc.spring.study.validation.annotation.ExistMember;
import umc.spring.study.validation.annotation.ExistMission;
import umc.spring.study.validation.annotation.ExistStore;
import umc.spring.study.web.Dto.RegionDTO.RegionRequestDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreRequestDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreResponseDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreSaveReponseDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreRestController {
    private final StoreCommandService  storeCommandService;
    private final StoreQueryServiceImpl storeQueryService;
    private final MemberRepository memberRepository;
    private final RegionService regionService;
    private final MissionService missionService;
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReveiwDTO request,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }
    @PostMapping("/save")
    public void createStore(@RequestBody StoreSaveReponseDTO.CreateStoreResultDTO request){
        Store store = storeQueryService.createStore(1L,request);
    }
    @PostMapping("/save/region")
    public void createRegion(@RequestBody RegionRequestDTO.RegionDTO request){
        Region region = regionService.createRegion(request);
    }
    @PostMapping("save/mission")
    public void createMission(@RequestBody StoreRequestDTO.MissionDTO request){
        Mission mission = missionService.createMission(2L,request);

    }
    @PostMapping("save/myMission")
    public void createChallenging(@RequestBody @Valid StoreRequestDTO.ChallengingDTO request){
        Mission mission = missionService.findById(request.getId());
        Member member = memberRepository.findById(1L).get();
        missionService.setMymission(mission,member);


    }
}


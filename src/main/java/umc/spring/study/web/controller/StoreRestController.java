package umc.spring.study.web.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.MissionConverter;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.*;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.repository.MemberMissionRepository;
import umc.spring.study.repository.MemberRepository;
import umc.spring.study.service.MemberService.MemberCommandServiceImpl;
import umc.spring.study.service.MissionService.MissionService;
import umc.spring.study.service.RegionService.RegionService;
import umc.spring.study.service.StoreService.StoreCommandService;
import umc.spring.study.service.StoreService.StoreQueryServiceImpl;
import umc.spring.study.validation.annotation.CheckPage;
import umc.spring.study.validation.annotation.ExistMember;
import umc.spring.study.validation.annotation.ExistMission;
import umc.spring.study.validation.annotation.ExistStore;
import umc.spring.study.web.Dto.RegionDTO.RegionRequestDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreRequestDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreResponseDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreSaveReponseDTO;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreRestController {
    private final StoreCommandService  storeCommandService;
    private final StoreQueryServiceImpl storeQueryService;
    private final MemberRepository memberRepository;
    private final RegionService regionService;
    private final MissionService missionService;
    private final MemberMissionRepository memberMissionRepository;
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReveiwDTO request,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO = StoreConverter.reviewPreViewListDTO(reviewList);
        return ApiResponse.onSuccess(reviewPreViewListDTO);
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
        Long missionId = missionService.createMission(2L, request);
        Member member = memberRepository.findById(1L).get();
        Mission mission = missionService.findById(missionId);
        missionService.setMymission(mission,member);
    }
    @PostMapping("save/myMission")
    public void createChallenging(@RequestBody @Valid StoreRequestDTO.ChallengingDTO request){
        Mission mission = missionService.findById(request.getId());
        Member member = memberRepository.findById(1L).get();
        missionService.setMymission(mission,member);

    }

    @GetMapping("/{memberId}/memberReviews")
    @Operation(summary = "사용자 리뷰 목록 조회 API",description = "사용자 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> MemberReviewList(@ExistMember @PathVariable(name = "memberId") Long memberId,@CheckPage @RequestParam(name = "page")Integer page){
        Page<Review> memberReviewList = storeQueryService.getMemberReviewList(memberId, page);
        StoreConverter.reviewPreViewListDTO(memberReviewList);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(memberReviewList));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "가게 미션 조회",description = "가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.MissionPreviewListDTO> storeMission(@ExistMember @PathVariable(name = "storeId") Long storeId,@CheckPage @RequestParam(name = "page")Integer page){
        Page<Mission> missionList = storeQueryService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.missionPreviewListDTO(missionList));

    }

    @GetMapping("/{memberId}/member_missions")
    @Operation(summary = "사용자 미션 조회",description = "사용자 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.MemberMissionPreviewListDTO> memberMission(@ExistMember @PathVariable(name = "memberId") Long memberId,@CheckPage @RequestParam(name = "page")Integer page){
        Page<MemberMission> missionList = storeQueryService.getMemberMissionList(memberId, page);
        return ApiResponse.onSuccess(StoreConverter.memberMissionPreviewListDTO(missionList));
    }

    @PostMapping("/complete_mission")
    @Operation(summary = "미션 complete으로 변경",description = "미션을 다하면 complete으로 변경하는 것입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })

    public ApiResponse<StoreResponseDTO.MemberMissionPreviewDTO> complete_mission(@RequestBody StoreRequestDTO.ChallengingDTO request){
        MemberMission memberMission = memberMissionRepository.findById(request.getId()).get();
        memberMission.setComplete();
        memberMissionRepository.save(memberMission);
        return ApiResponse.onSuccess(MissionConverter.completeMission(memberMission));
    }

}


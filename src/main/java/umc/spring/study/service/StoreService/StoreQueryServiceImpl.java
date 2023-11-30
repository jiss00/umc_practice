package umc.spring.study.service.StoreService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.repository.*;
import umc.spring.study.service.MemberService.MemberQueryServiceImpl;
import umc.spring.study.service.MissionService.MissionService;
import umc.spring.study.service.RegionService.RegionService;
import umc.spring.study.web.Dto.StoreDTO.StoreSaveReponseDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final RegionService regionService;
    private final MemberRepository memberRepository;
    private final MemberQueryServiceImpl memberQueryService;
    private final ReviewRepository reviewRepository;
    private final MissionService missionService;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;


    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }


    @Transactional
    public Store createStore(Long RegionId,StoreSaveReponseDTO.CreateStoreResultDTO request) {
        Store store = StoreConverter.saveStore(request);
        store.setRegion(regionService.findById(RegionId).get());
        return storeRepository.save(store);
    }
    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();
        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }
    @Override
    public Page<Review> getMemberReviewList(Long memberId, Integer page) {
        Member member = memberQueryService.findMember(memberId).get();
        Page<Review> StorePage = reviewRepository.findAllByMember(member,PageRequest.of(page-1,10));
        return StorePage;
    }
    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId).get();
        return missionRepository.findAllByStore(store,PageRequest.of(page-1,10));
    }
    @Override
    public Page<MemberMission> getMemberMissionList(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId).get();
        return memberMissionRepository.findAllByMember(member,PageRequest.of(page-1,10));
    }

}

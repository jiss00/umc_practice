package umc.spring.study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.web.Dto.StoreDTO.StoreSaveReponseDTO;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    Store createStore (Long RegionId,StoreSaveReponseDTO.CreateStoreResultDTO request);
    Page<Review> getReviewList(Long StoreId, Integer page);
    Page<Review> getMemberReviewList(Long memberId,Integer page);
    Page<Mission> getMissionList(Long MissionId, Integer page);

    Page<MemberMission> getMemberMissionList(Long memberId, Integer page);
}
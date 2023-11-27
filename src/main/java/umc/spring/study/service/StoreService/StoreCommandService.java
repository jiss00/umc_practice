package umc.spring.study.service.StoreService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.Review;
import umc.spring.study.web.Dto.StoreDTO.StoreRequestDTO;


public interface StoreCommandService {
    Review createReview(Long memberId,Long storeId, StoreRequestDTO.ReveiwDTO request);
}

package umc.spring.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.Review;
import umc.spring.study.repository.MemberRepository;
import umc.spring.study.repository.ReviewRepository;
import umc.spring.study.repository.StoreRepository;
import umc.spring.study.web.Dto.StoreDTO.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;

    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDTO request) {

        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }
}

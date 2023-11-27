package umc.spring.study.service.StoreService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.Store;
import umc.spring.study.repository.StoreRepository;
import umc.spring.study.service.RegionService.RegionService;
import umc.spring.study.web.Dto.StoreDTO.StoreSaveReponseDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final RegionService regionService;

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

}

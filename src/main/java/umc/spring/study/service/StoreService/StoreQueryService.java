package umc.spring.study.service.StoreService;

import umc.spring.study.domain.Store;
import umc.spring.study.web.Dto.StoreDTO.StoreSaveReponseDTO;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    Store createStore (Long RegionId,StoreSaveReponseDTO.CreateStoreResultDTO request);
}
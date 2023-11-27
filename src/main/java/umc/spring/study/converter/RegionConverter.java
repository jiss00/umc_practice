package umc.spring.study.converter;

import umc.spring.study.domain.Region;
import umc.spring.study.domain.Store;
import umc.spring.study.web.Dto.RegionDTO.RegionRequestDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreSaveReponseDTO;

public class RegionConverter {
    public static Region saveRegion(RegionRequestDTO.RegionDTO request){
        return Region
                .builder()
                .name(request.getName())
                .build();
    }
}

package umc.spring.study.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.converter.RegionConverter;
import umc.spring.study.domain.Region;
import umc.spring.study.repository.RegionRepository;
import umc.spring.study.web.Dto.RegionDTO.RegionRequestDTO;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    @Transactional
    public Region createRegion(RegionRequestDTO.RegionDTO request){
        Region region = RegionConverter.saveRegion(request);
        return regionRepository.save(region);

    }
    public Optional<Region> findById(Long regionId){
        return regionRepository.findById(regionId);
    }

}

package umc.spring.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.converter.MissionConverter;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.repository.MemberMissionRepository;
import umc.spring.study.repository.MissionRepository;
import umc.spring.study.repository.StoreRepository;
import umc.spring.study.web.Dto.StoreDTO.StoreRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public Long createMission(Long storeId, StoreRequestDTO.MissionDTO request){
        Mission mission = StoreConverter.saveMission(request);
        mission.setStore(storeRepository.findById(storeId).get());
        missionRepository.save(mission);
        return mission.getId();
    }
    @Transactional
    public MemberMission setMymission(Mission mission, Member member){
        MemberMission memberMission = MissionConverter.challengingMission();
        memberMission.setMission(mission);
        memberMission.setMember(member);
        return memberMissionRepository.save(memberMission);

    }
    public Mission findById(Long missionId){
        return missionRepository.findById(missionId).get();
    }
}

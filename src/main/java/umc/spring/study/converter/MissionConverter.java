package umc.spring.study.converter;

import umc.spring.study.domain.Enum.MissionStatus;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.web.Dto.StoreDTO.StoreRequestDTO;
import umc.spring.study.web.Dto.StoreDTO.StoreResponseDTO;

public class MissionConverter {
    public static MemberMission challengingMission(){
        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
    }
    public static StoreResponseDTO.MemberMissionPreviewDTO completeMission(MemberMission mission){
        return StoreResponseDTO.MemberMissionPreviewDTO.builder()
                .missionStatus(mission.getStatus())
                .build();
    }
}

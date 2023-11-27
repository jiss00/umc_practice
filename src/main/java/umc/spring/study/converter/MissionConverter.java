package umc.spring.study.converter;

import umc.spring.study.domain.Enum.MissionStatus;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.web.Dto.StoreDTO.StoreRequestDTO;

public class MissionConverter {
    public static MemberMission challengingMission(){
        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}

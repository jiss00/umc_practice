package umc.spring.study.domain.mapping;

import lombok.*;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Common.BaseEntity;
import umc.spring.study.domain.Enum.MissionStatus;
import umc.spring.study.domain.Mission;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private MissionStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMission(Mission mission){
        if(this.mission != null){
            mission.getMemberMissionList().remove(this);
        }
        this.mission = mission;
        mission.getMemberMissionList().add(this);
    }
    public void setMember(Member member){
        if(this.member != null){
            member.getMemberMissionList().remove(this);
        }
        this.member = member;
        member.getMemberMissionList().add(this);
    }
}

package umc.spring.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission,Long> {
    Page<MemberMission> findAllByMember(Member member, PageRequest pageRequest);
}

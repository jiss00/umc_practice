package umc.spring.study.domain;

import lombok.*;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.domain.Common.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer reward;

    private LocalDate deadline;

    private String missionSpec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
    public void setStore(Store store){
        if(this.store != null){
            store.getMissionList().remove(store);
        }
        this.store = store;
        store.getMissionList().add(this);

    }
}

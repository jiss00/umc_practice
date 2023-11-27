package umc.spring.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}

package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<AreaEntity, Integer> {
    int deleteByName(String name);
    boolean existsByName(String name);
    AreaEntity findByName(String name);
}

package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<AreaEntity, Integer> {

    boolean existsByName(String name);
}

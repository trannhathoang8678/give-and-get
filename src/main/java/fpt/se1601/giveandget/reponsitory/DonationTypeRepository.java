package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.DonationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationTypeRepository extends JpaRepository<DonationTypeEntity, Integer> {
    int deleteByName(String name);

    boolean existsByName(String name);
}
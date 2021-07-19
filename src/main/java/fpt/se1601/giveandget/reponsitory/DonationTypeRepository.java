package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.DonationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationTypeRepository extends JpaRepository<DonationTypeEntity, Integer> {

    boolean existsByName(String name);
}
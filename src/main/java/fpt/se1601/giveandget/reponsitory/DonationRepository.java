package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DonationRepository extends JpaRepository<DonationEntity, Integer> {
    Page<DonationEntity> findAll(Pageable pageable);
}

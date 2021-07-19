package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.AreaEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Integer> {
    Page<DonationEntity> findAll(Pageable pageable);

    DonationEntity findOneById(int id);

    List<DonationEntity> findByTypeId(int typeId, Pageable pageable);

    List<DonationEntity> findByAreaId(int areaId, Pageable pageable);

    List<DonationEntity> findByNameLike(String name);
    @Query(nativeQuery = true, value = "SELECT COUNT(`id`) FROM `DONATION` GROUP BY `is_received` HAVING `is_received` = 1")
    int getNumberReceiveDonation();
    List<DonationEntity> findByIsReceived(boolean isReceived);
}

package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.AreaEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DonationRepository extends JpaRepository<DonationEntity, Integer> {
    Page<DonationEntity> findAll(Pageable pageable);

    DonationEntity findOneById(int id);

    List<DonationEntity> findByTypeId(int typeId, Pageable pageable);

    List<DonationEntity> findByAreaId(int areaId, Pageable pageable);

    List<DonationEntity> findByNameLike(String name);
}

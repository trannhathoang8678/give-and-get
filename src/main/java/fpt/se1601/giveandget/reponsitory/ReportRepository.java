package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.ReportEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {
    List<ReportEntity> findAll(Sort sort);
    List<ReportEntity> findByDonation(DonationEntity donationEntity);
    @Query(nativeQuery = true, value = "SELECT `USER_id` FROM REPORT WHERE `id` = ?1")
    int findUserIdById(int donationId);
}

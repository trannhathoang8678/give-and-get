package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity,Integer> {
    List<ReportEntity> findByDonation(DonationEntity donation);
    @Query(nativeQuery = true, value = "SELECT `USER_id` FROM REPORT WHERE `id` = ?1")
    int findUserIdById(int donationId);
}

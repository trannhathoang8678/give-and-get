package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.RelationshipEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RelationshipRepository extends JpaRepository<RelationshipEntity, Integer> {
    List<RelationshipEntity> findByUser(UserEntity user);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT 1 FROM RELATIONSHIP WHERE `USER_id`=?1 AND `DONATION_id`=?2 AND `is_donor`=?3 LIMIT 1)")
    short existsRelationship(int userId, int donationId, short isDonor);

    @Query(nativeQuery = true, value = "SELECT `USER_id` FROM RELATIONSHIP WHERE  `DONATION_id`=?1 AND `is_donor`=1 LIMIT 1)")
    short findUserIdDonorDonation( int donationId);

    @Query(nativeQuery = true, value = "SELECT `id` FROM RELATIONSHIP WHERE  `USER_id`=?1 AND `DONATION_id`=?2 AND `is_donor`=0 LIMIT 1)")
    int findIdOfSaveRelationship(int userId, int donationId);
}

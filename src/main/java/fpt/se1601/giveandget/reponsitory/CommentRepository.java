package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT `USER_id` FROM COMMENT WHERE `id` = ?1")
    int findUserIdById(int donationId);
}

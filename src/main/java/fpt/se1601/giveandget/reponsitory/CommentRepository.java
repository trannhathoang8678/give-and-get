package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Query(nativeQuery = true, value = "UPDATE COMMENT SET 'content' = ?2 WHERE 'id' = ?1;")
    CommentEntity updateContent(int id, String newContent);
}

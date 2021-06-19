package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.RelationshipEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RelationshipRepository extends JpaRepository<RelationshipEntity,Integer> {
    List<RelationshipEntity> findByUser(UserEntity user);
}

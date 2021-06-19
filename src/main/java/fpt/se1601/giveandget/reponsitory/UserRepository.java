package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByRole(String Role);

    UserEntity findOneByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT `TOKEN_id` FROM USER WHERE email = ?1")
    int findTokenIdByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT `role` FROM USER WHERE `TOKEN_id` = ?1")
    String findRoleByTokenId(int tokenId);
    @Query(nativeQuery = true, value = "SELECT `id` FROM USER WHERE `TOKEN_id` = ?1")
    int findUserIdByTokenId(int tokenId);
    boolean existsByEmail(String email);
}

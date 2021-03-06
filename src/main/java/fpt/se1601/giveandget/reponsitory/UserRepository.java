package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findOneById(int id);

    UserEntity findOneByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM USER WHERE `TOKEN_id` = ?1 ;")
    UserEntity findByTokenId(int tokenId);

    @Query(nativeQuery = true, value = "SELECT `TOKEN_id` FROM USER WHERE email = ?1 ;")
    int findTokenIdByEmail(String email);


    @Query(nativeQuery = true, value = "SELECT `role` FROM USER WHERE `TOKEN_id` = ?1 ;")
    String findRoleByTokenId(int tokenId);

    @Query(nativeQuery = true, value = "SELECT `id` FROM USER WHERE `TOKEN_id` = ?1 ;")
    int findUserIdByTokenId(int tokenId);

    @Query(nativeQuery = true, value = "SELECT `name` FROM USER WHERE `id` = ?1 ;")
    String findNameById(int id);

    @Query(nativeQuery = true, value = "SELECT `email` FROM USER WHERE `id` = ?1 ;")
    String findEmailById(int id);

    boolean existsByEmail(String email);
}

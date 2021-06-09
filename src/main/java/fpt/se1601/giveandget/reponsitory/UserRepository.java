package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRole(String Role);
    User findOneByEmail(String email);
    @Query(nativeQuery = true, value = "SELECT `TOKEN_id` FROM USER WHERE phone = ?1")
    int findTokenIdByEmail(String email);
    User findOneByEmailAndPassword(String email,String password);
    boolean existsByEmail(String email);
}

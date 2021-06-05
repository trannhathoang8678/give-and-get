package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRole(String Role);
    User findOneByPhone(String phone);
}

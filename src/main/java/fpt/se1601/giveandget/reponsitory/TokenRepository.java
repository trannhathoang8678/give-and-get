package fpt.se1601.giveandget.reponsitory;

import fpt.se1601.giveandget.reponsitory.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity,Integer> {
    TokenEntity findOneById(int id);
    @Query(nativeQuery = true, value = "SELECT `id` FROM TOKEN WHERE token = ?1")
    int findTokenId(String token);
}

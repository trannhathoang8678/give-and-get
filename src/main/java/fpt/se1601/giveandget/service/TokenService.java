package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.reponsitory.TokenRepository;
import fpt.se1601.giveandget.reponsitory.UserRepository;
import fpt.se1601.giveandget.reponsitory.entity.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UserRepository userRepository;
    public String addTokenForUserHasEmail(String userEmail) {
        try {
            int tokenId = userRepository.findTokenIdByEmail(userEmail);
            TokenEntity tokenEntity = tokenRepository.findOneById(tokenId);
            String sessionToken = UUID.randomUUID().toString();
            tokenEntity.setToken(sessionToken);
            tokenRepository.save(tokenEntity);
            return sessionToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTokenRole(String token) {
        try {
            return userRepository.findRoleByTokenId(tokenRepository.findTokenId(token));
        } catch (Exception e) {
            System.out.println("Token is not existed");
            return null;
        }
    }
}

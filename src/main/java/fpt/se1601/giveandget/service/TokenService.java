package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.reponsitory.TokenRepository;
import fpt.se1601.giveandget.reponsitory.UserRepository;
import fpt.se1601.giveandget.reponsitory.entity.Token;
import fpt.se1601.giveandget.reponsitory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UserRepository userRepository;

    public Token findTokenByEmail(String email) {
        try {
            return tokenRepository.findOneById(userRepository.findTokenIdByEmail(email));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String addTokenForUserHasEmail(String userEmail) {
        try {
            int tokenId = userRepository.findTokenIdByEmail(userEmail);
            Token token = tokenRepository.findOneById(tokenId);
            String sessionToken =UUID.randomUUID().toString();
            token.setToken(sessionToken);
            tokenRepository.save(token);
            return  sessionToken;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.reponsitory.TokenRepository;
import fpt.se1601.giveandget.reponsitory.UserRepository;
import fpt.se1601.giveandget.reponsitory.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UserRepository userRepository;
    public Token findTokenByEmail(String email){
        try {
            return tokenRepository.findOneById(userRepository.findTokenIdByEmail(email));
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

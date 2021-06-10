package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.reponsitory.UserRepository;
import fpt.se1601.giveandget.reponsitory.entity.Token;
import fpt.se1601.giveandget.reponsitory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SendEmailService sendEmailService;
    private static final String DATA_FOR_RANDOM_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static SecureRandom random = new SecureRandom();
    public List<User> getUsersHaveRole(String role) {
        try {
            return userRepository.findByRole(role);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public User login(String email,String password){
        try {
             User user = userRepository.findOneByEmail(email);
             if(user == null) return null;
             if(new BCryptPasswordEncoder().matches(password, user.getPassword()))
                 return user;
              return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public boolean isEmailExists(String email){
        try {
            return userRepository.existsByEmail(email);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }
    public String register(String email, String password, String token) {
        User temporaryUser = userRepository.findOneByEmail(email);
        if (temporaryUser == null)
            return "Please click sent token button";
        if (temporaryUser.getPassword() != null)
            return "Email has already regitered";
        String sentToken = temporaryUser.getToken().getToken();
        if (!sentToken.equals(token))
            return "Wrong token, please check or send another token ";
        temporaryUser.setPassword(new BCryptPasswordEncoder().encode(password));
        userRepository.save(temporaryUser);
        return "Register success";
    }

    public String sendTokenToEmail(String email) {
        try
        {User temporaryUser = userRepository.findOneByEmail(email);
            Token tokenEntity = new Token();
            String token = generateRandomString(6);
            tokenEntity.setToken(token);
            if (temporaryUser == null) {
                temporaryUser = new User(email, tokenEntity);
            } else {
                tokenEntity.setToken(token);
            }
            userRepository.save(temporaryUser);
            sendEmailService.sendEmail("Token to verify Give And Get website",email,token);
            return "Sent token to email";}
        catch (Exception e)
        {
            e.printStackTrace();
            return "Send token failed";
        }
    }
    public String retrievePassword(String email,String token) {
        sendTokenToEmail(email);
        User temporaryUser = userRepository.findOneByEmail(email);
        if ( temporaryUser.getPassword() == null)
            return "Email have not been registerd";
        String sentToken = temporaryUser.getToken().getToken();
        if (!sentToken.equals(token))
            return "Wrong token, please check or send another token ";
        return "Password: " + temporaryUser.getPassword();
    }
}


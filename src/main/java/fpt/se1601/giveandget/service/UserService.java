package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.controller.request.RegisterRequest;
import fpt.se1601.giveandget.controller.request.UserInfoRequest;
import fpt.se1601.giveandget.reponsitory.*;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.RelationshipEntity;
import fpt.se1601.giveandget.reponsitory.entity.TokenEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    RelationshipRepository relationshipRepository;
    @Autowired
    SendEmailService sendEmailService;
    private static final String DATA_FOR_RANDOM_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static SecureRandom random = new SecureRandom();

    public UserEntity login(String email, String password) {
        try {
            UserEntity userEntity = userRepository.findOneByEmail(email);
            if (userEntity == null) return null;
            if (new BCryptPasswordEncoder().matches(password, userEntity.getPassword()))
                return userEntity;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isEmailExists(String email) {
        try {
            return userRepository.existsByEmail(email);
        } catch (Exception e) {
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

    public String register(RegisterRequest registerRequest) {
        UserEntity temporaryUserEntity = userRepository.findOneByEmail(registerRequest.getEmail());
        if (temporaryUserEntity == null)
            return "Please click sent token button";
        if (temporaryUserEntity.getPassword() != null)
            return "Email has already regitered";
        String sentToken = temporaryUserEntity.getTokenEntity().getToken();
        if (!sentToken.equals(registerRequest.getToken()))
            return "Wrong token, please check or send another token ";
        temporaryUserEntity.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
        temporaryUserEntity.setRole("MEMBER");
        temporaryUserEntity.setName(registerRequest.getName());
        userRepository.save(temporaryUserEntity);
        return "Register success";
    }

    public String sendTokenToEmail(String email) {
        try {
            UserEntity temporaryUserEntity = userRepository.findOneByEmail(email);
            TokenEntity tokenEntity;
            String token = generateRandomString(6);

            if (temporaryUserEntity == null) {
                tokenEntity = new TokenEntity();
                temporaryUserEntity = new UserEntity(email, tokenEntity);
            } else {
                tokenEntity = temporaryUserEntity.getTokenEntity();
            }
            tokenEntity.setToken(token);
            userRepository.save(temporaryUserEntity);
            sendEmailService.sendEmail("Token to verify Give And Get website", email, token);
            return "Sent token to email";
        } catch (Exception e) {
            e.printStackTrace();
            return "Send token failed";
        }
    }

    public String updateUserRole(int userId, String role) {
        try {
            UserEntity user = userRepository.findOneById(userId);
            user.setRole(role);
            userRepository.save(user);
            return "Update role success";
        } catch (Exception e) {

            return "Update role failed. Error: " + e.getMessage();
        }
    }

    public String retrievePassword(String email, String token, String newPassword) {
        try {
            UserEntity temporaryUserEntity = userRepository.findOneByEmail(email);
            if (temporaryUserEntity.getPassword() == null)
                return "Email have not been register";
            String sentToken = temporaryUserEntity.getTokenEntity().getToken();
            if (!sentToken.equals(token))
                return "Wrong token, please check or send another token ";
            return updatePassword(email, temporaryUserEntity.getPassword(), newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return "Email have not been register";
        }
    }

    public String updatePassword(String email, String oldPassword, String newPassword) {
        try {
            UserEntity userEntity = userRepository.findOneByEmail(email);
            if (userEntity.getPassword() != oldPassword && new BCryptPasswordEncoder().matches(oldPassword,userEntity.getPassword()))
                return "Your current password is false";
            userEntity.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userRepository.save(userEntity);
                return "Update password success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in update: " + e.getMessage();
        }
    }

    public int findUserIdByToken(String token) {
        try {
            return userRepository.findUserIdByTokenId(tokenRepository.findTokenId(token));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<UserEntity> getAllUsers() {
        try {

            return userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserEntity updateUserInfo(UserInfoRequest userInfoRequest) {
        try {
            UserEntity user = userRepository.findOneById(userInfoRequest.getId());
            user.setName(userInfoRequest.getName());
            user.setPhone(userInfoRequest.getPhone());
            user.setAge(userInfoRequest.getAge());
            user.setSex(userInfoRequest.getSex());
            user.setLinkContactInfo(userInfoRequest.getLinkContactInfo());
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserEntity updateAvatarUser(UserEntity user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteUser(int userId) {
        try {
            userRepository.deleteById(userId);
            List<RelationshipEntity> relationships = relationshipRepository.findByUserId(userId);
            if (relationships != null)
                for (RelationshipEntity relationship : relationships)
                    relationshipRepository.deleteById(relationship.getId());
            return "Delete user successfully";
        } catch (Exception e) {
            return "Delete user failed";
        }
    }

    public UserEntity getUserByToken(String token) {
        try {
            return userRepository.findByTokenId(tokenRepository.findTokenId(token));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isDonationOfUser(int userId, int donationId) {
        try {
            if (relationshipRepository.existsRelationship(userId, donationId, (short) 1) != 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<DonationEntity> getDonationsOfUser(int userId) {
        try {
            List<DonationEntity> donations = new LinkedList<>();
            List<RelationshipEntity> relationshipEntities = relationshipRepository.findByUserId(userId);
            if (relationshipEntities == null)
                return null;
            for (RelationshipEntity relationship : relationshipEntities)
                donations.add(relationship.getDonation());
            return donations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}


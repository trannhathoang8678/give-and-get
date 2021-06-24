package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.controller.request.RegisterRequest;
import fpt.se1601.giveandget.controller.request.UserInfoRequest;
import fpt.se1601.giveandget.interceptor.GatewayConstant;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import fpt.se1601.giveandget.service.DonationService;
import fpt.se1601.giveandget.service.FileStorageService;
import fpt.se1601.giveandget.service.TokenService;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;
    @Autowired
    DonationService donationService;
    @Autowired
    FileStorageService fileStorageService;
    @GetMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        try {

            if (userService.login(email, password) != null) {
                response.addHeader(GatewayConstant.AUTHORIZATION_HEADER, tokenService.addTokenForUserHasEmail(email));
                return "Login success";
            }
            if (userService.isEmailExists(email))
                return "Password is wrong";
            return "Email is not exist";
        } catch (Exception e) {
            e.printStackTrace();
            return "Login error";
        }
    }

    @PutMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        try {
            return userService.register(registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getToken());
        } catch (Exception e) {
            e.printStackTrace();
            return "Register error";
        }
    }

    @PostMapping("/token")
    public String sendTokenToEmail(String email) {
        try {
            return userService.sendTokenToEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return "Register error";
        }
    }
    @PutMapping(value = "/retrieve")
    public String retrievePassword(@RequestBody RegisterRequest registerRequest){
        try {
            return userService.retrievePassword(registerRequest.getEmail(),registerRequest.getPassword());
        }
        catch (Exception e){
            e.printStackTrace();
            return "Retrieve password failed";
        }
    }
    @GetMapping
    public UserEntity getUserInfo(@RequestHeader("Authorization") String token) {
        try {
            return userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping
    public UserEntity updateUserInfo(@RequestHeader("Authorization") String token, @RequestBody UserInfoRequest userInfoRequest) {
        try {
            if (userService.findUserIdByToken(token) == userInfoRequest.getId())
                return userService.updateUserInfo(userInfoRequest);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping(value= "/avatar")
    public String setAvatarOfUser(@RequestHeader("Authorization") String token,@RequestParam("image") MultipartFile image){
        try{
            UserEntity userEntity = userService.getUserByToken(token);
            userEntity.setAvatar(fileStorageService.save(image,"user" + userEntity.getId() + "_avatar"));
            userService.updateAvatarUser(userEntity);
            return "Set avatar success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Set avatar failed";
        }
    }
    @GetMapping("/index")
    public String test() {
        System.out.println("test oke");
        return "test auth";
    }
    @GetMapping(value = "/user/donation")
    public List<DonationEntity> getDonationRelatedToUser(@RequestHeader("Authorization") String token){
        try {
            return donationService.getDonationsRelatedToUser(userService.findUserIdByToken(token));
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
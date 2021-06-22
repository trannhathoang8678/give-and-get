package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.controller.request.DonationRequest;
import fpt.se1601.giveandget.controller.request.UserInfoRequest;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import fpt.se1601.giveandget.service.DonationService;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    @Autowired
    DonationService donationService;
    @Autowired
    UserService userService;


    @PostMapping(value = "/donation")
    public String addDonation(@RequestHeader("Authorization") String token, @RequestBody DonationRequest donationRequest) {
        DonationEntity donationEntity = donationService.addDonation(donationRequest);
        if (donationEntity == null)
            return "Add donation failed";
        if (donationService.addRelationship(userService.findUserIdByToken(token), donationEntity.getId(), (short) 1) == null)
            return "Add relationship failed but add donation successfully";
        return "Add donation successfully";
    }

    @PutMapping(value = "/donation")
    public String updateDonation(@RequestHeader("Authorization") String token, @RequestBody DonationRequest donationRequest) {
        try {
            if(!userService.isDonationOfUser(userService.findUserIdByToken(token),donationRequest.getId()))
                return "It is not your donation";
            if (donationService.updateDonation(donationRequest) != null)
                return "Update donation success";
            else
                return "Update donation failed";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update donation failed";
        }
    }

    @DeleteMapping(value = "/donation")
    public String deleteDonation(@RequestHeader("Authorization") String token,@RequestBody int id) {
        try {
            if(!userService.isDonationOfUser(userService.findUserIdByToken(token),id))
                return "It is not your donation";
            if (donationService.deleteDonation(id) != null)
                return "Update donation success";
            return "Update donation failed";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update donation failed";
        }
    }

    @GetMapping(value = "/user")
    public UserEntity getUserInfo(@RequestHeader("Authorization") String token) {
        try {
            return userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping(value = "/user")
    public UserEntity updateUserInfo(@RequestHeader("Authorization") String token, @RequestBody UserInfoRequest userInfoRequest) {
        try {
            if(userService.findUserIdByToken(token) == userInfoRequest.getId())
            return userService.updateUserInfo(userInfoRequest);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.controller.request.DonationRequest;
import fpt.se1601.giveandget.controller.request.UserInfoRequest;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import fpt.se1601.giveandget.service.DonationService;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (donationService.addRelationship(userService.findUserIdByToken(token), donationEntity.getId(),(short)1) == null)
            return "Add relationship failed but add donation successfully";
        return "Add donation successfully";
    }

    @PutMapping(value = "/donation")
    public String updateDonation(@RequestBody DonationRequest donationRequest) {
        if (donationService.updateDonation(donationRequest) != null)
            return "Update donation success";
        else
            return "Update donation failed";
    }

    @DeleteMapping(value = "/donation")
    public String deleteDonation(@RequestBody int id) {
        try {
            if (donationService.deleteDonation(id) != null)
                return "Update donation success";
            return "Update donation failed";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update donation failed";
        }
    }
    @PutMapping
    public UserEntity updateUserInfo(UserInfoRequest userInfoRequest){
        try{
            return userService.updateUserInfo(userInfoRequest);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

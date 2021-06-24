package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.controller.request.DonationRequest;
import fpt.se1601.giveandget.controller.request.UserInfoRequest;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import fpt.se1601.giveandget.service.DonationService;
import fpt.se1601.giveandget.service.FileStorageService;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/member")
public class MemberController {
    @Autowired
    DonationService donationService;
    @Autowired
    UserService userService;
    @Autowired
    FileStorageService fileStorageService;

    @PostMapping(value = "/donation")
    public String addDonation(@RequestHeader("Authorization") String token, @RequestBody DonationRequest donationRequest,
                              @RequestParam("images") MultipartFile images[]) {
        try {
            int userId = userService.findUserIdByToken(token);
            donationRequest.setLinkImages(fileStorageService.save(images, "donation_" + userId));
            DonationEntity donationEntity = donationService.addDonation(donationRequest);
            if (donationEntity == null)
                return "Add donation failed";
            if (donationService.addRelationship(userId, donationEntity.getId(), (short) 1) == null)
                return "Add relationship failed but add donation successfully";
            return "Add donation successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping(value = "/donation")
    public String updateDonation(@RequestHeader("Authorization") String token, @RequestBody DonationRequest donationRequest,
                                 @RequestParam("images") MultipartFile images[]) {
        try {
            if (!userService.isDonationOfUser(userService.findUserIdByToken(token), donationRequest.getId()))
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
    public String deleteDonation(@RequestHeader("Authorization") String token, @RequestBody int id) {
        try {
            if (!userService.isDonationOfUser(userService.findUserIdByToken(token), id))
                return "It is not your donation";
            if (donationService.deleteDonation(id) != null)
                return "Update donation success";
            return "Update donation failed";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update donation failed";
        }
    }

    @GetMapping(value = "/donation")
    public List<DonationEntity> getDonationOfUser(@RequestHeader("Authorization") String token) {
        try {
            return userService.getDonationsOfUser(userService.findUserIdByToken(token));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

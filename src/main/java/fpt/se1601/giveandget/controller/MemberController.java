package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.controller.request.DonationRequest;
import fpt.se1601.giveandget.controller.request.ReactionRequest;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.service.DonationService;
import fpt.se1601.giveandget.service.FileStorageService;
import fpt.se1601.giveandget.service.ReactionService;
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
    @Autowired
    ReactionService reactionService;

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
    @DeleteMapping(value = "/relationship")
    public String unsaveDonation(@RequestHeader("Authorization") String token, @RequestBody int id) {
        try {
            int userId = userService.findUserIdByToken(token);
            if (!userService.isDonationOfUser(userId, id))
                return "You have not saved this donation";
            if (donationService.deleteSaveRelationship(userId,id) != null)
                return "Unsave donation success";
            return "Unsave donation failed";
        } catch (Exception e) {
            e.printStackTrace();
            return "Unsave donation failed";
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
    @PutMapping(value = "/donation/receive")
    public String setReceiveStatusOfDonation(@RequestHeader("Authorization") String token, @RequestBody int id){
        try {
            if (!userService.isDonationOfUser(userService.findUserIdByToken(token), id))
                return "It is not your donation";
            return donationService.setReceiveStatusOfDonation(id);

        } catch (Exception e) {
            return "Update donation failed"+e.getMessage();
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

    @PostMapping(value = "/donation/save")
    public String saveDonation(@RequestHeader("Authorization") String token, @RequestBody int id) {
        try {
            int userId = userService.findUserIdByToken(token);
            if (!userService.isDonationOfUser(userId, id))
                return "It is already your donation";
            if (donationService.addRelationship(userId, id, (short) 0) != null)
                return "Save donation success";
            return "Save donation failed";
        } catch (Exception e) {
            e.printStackTrace();
            return "Save donation failed";
        }
    }

    @PostMapping(value = "/report")
    public String addReport(@RequestHeader("Authorization") String token, @RequestBody ReactionRequest reactionRequest) {
        try {
            reactionRequest.setUserId(userService.findUserIdByToken(token));
            return reactionService.addReport(reactionRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(value = "/report")
    public String deleteComment(@RequestHeader("Authorization") String token, int reportId) {
        try {
            if (reactionService.isReportOfUser(userService.findUserIdByToken(token), reportId)) {
                reactionService.deleteReport(reportId);
                return "Delete comment success";
            }
            return "It is not your comment";
        } catch (Exception e) {
            return "Delete comment fail. Error: " + e.getMessage();
        }
    }

}

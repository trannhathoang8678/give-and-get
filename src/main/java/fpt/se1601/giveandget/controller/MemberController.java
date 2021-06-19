package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.controller.request.DonationRequest;
import fpt.se1601.giveandget.reponsitory.DonationRepository;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.service.DonationService;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    DonationService donationService;
    @Autowired
    UserService userService;
    @Autowired
    DonationRepository donationRepository;

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

    @GetMapping(value = "/donation")
    public List<DonationEntity> getDonationByCreatedTime(@RequestParam int fromItem, @RequestParam int toItem) {
        try {
            if (fromItem < 0) return null;
            toItem = Math.min(toItem, (int) donationRepository.count() - 1);
            if (fromItem > toItem) fromItem = toItem;
            Pageable pageable = PageRequest.of(fromItem, toItem, Sort.by("id").descending());
            return donationService.getDonationsByOrder(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/donation/type/{id}")
    public List<DonationEntity> getDonationByType(@PathVariable int id,@RequestParam int fromItem, @RequestParam int toItem) {
        try {
            if (fromItem < 0) return null;
            toItem = Math.min(toItem, (int) donationRepository.count() - 1);
            if (fromItem > toItem) fromItem = toItem;
            return donationService.getDonationsByTypeInOrder(id, PageRequest.of(fromItem,toItem,Sort.by("id").descending()));
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping(value = "/donation/area/{id}")
    public List<DonationEntity> getDonationByArea(@PathVariable int id,@RequestParam int fromItem, @RequestParam int toItem) {
        try {
            if (fromItem < 0) return null;
            toItem = Math.min(toItem, (int) donationRepository.count() - 1);
            if (fromItem > toItem) fromItem = toItem;
            return donationService.getDonationsByAreaInOrder(id, PageRequest.of(fromItem,toItem,Sort.by("id").descending()));
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping(value = "donation/{id}")
    public List<DonationEntity> getDonationRelatedToUser(@RequestHeader("Authorization") String token){
        try {
           return donationService.getDonationsRelatedToUser(userService.findUserIdByToken(token));
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping(value = "donation/name/{name}")
    public List<DonationEntity> getDonationHaveName(@PathVariable String name){
        try{
            return donationService.getDonationsHaveName(name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}

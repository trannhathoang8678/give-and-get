package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.controller.response.CommentResponse;
import fpt.se1601.giveandget.reponsitory.entity.AreaEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationTypeEntity;
import fpt.se1601.giveandget.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    DonationService donationService;

    @GetMapping(value = "/area")
    public List<AreaEntity> getAreas() {
        try {
            return donationService.getArea();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/type")
    public List<DonationTypeEntity> getDonationType() {
        try {
            return donationService.getDonationType();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/donation")
    public List<DonationEntity> getDonationByCreatedTime(@RequestParam int fromItem, @RequestParam int numberItem) {
        try {
            if (fromItem < 0) return null;
            numberItem = Math.min(numberItem, donationService.getNumberDonations());
            if (fromItem > numberItem) fromItem = numberItem;
            Pageable pageable = PageRequest.of(fromItem, numberItem, Sort.by("id").descending());
            return donationService.getDonationsByOrder(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/donation/number")
    public int getNumberDonations() { return donationService.getNumberDonations(); }

    @GetMapping(value = "/donation/{id}/comment")
    public List<CommentResponse> getCommentOfDonation(@PathVariable int id){
        return donationService.getCommentOfDonation(id);
    }
    @GetMapping(value = "/donation/type/{id}")
    public List<DonationEntity> getDonationByType(@PathVariable int id, @RequestParam int fromItem, @RequestParam int numberItem) {
        try {
            if (fromItem < 0) return null;
            numberItem = Math.min(numberItem, donationService.getNumberDonations());
            if (fromItem > numberItem) fromItem = numberItem;
            return donationService.getDonationsByTypeInOrder(id, PageRequest.of(fromItem, numberItem, Sort.by("id").descending()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/donation/area/{id}")
    public List<DonationEntity> getDonationByArea(@PathVariable int id, @RequestParam int fromItem, @RequestParam int numberItem) {
        try {
            if (fromItem < 0) return null;
            numberItem = Math.min(numberItem, (int) donationService.getNumberDonations());
            if (fromItem > numberItem) fromItem = numberItem;
            return donationService.getDonationsByAreaInOrder(id, PageRequest.of(fromItem, numberItem, Sort.by("id").descending()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/donation/name/{name}")
    public List<DonationEntity> getDonationHaveName(@PathVariable String name) {
        try {
            return donationService.getDonationsHaveName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

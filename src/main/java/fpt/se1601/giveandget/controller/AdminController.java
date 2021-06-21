package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.controller.request.DonationPropertiesRequest;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import fpt.se1601.giveandget.service.DonationService;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    DonationService donationService;
    @Autowired
    UserService userService;
    @PostMapping(value = "/area")
    public String addArea(@RequestBody String name)
    {
        if(donationService.isAreaHasNameExists(name))
            return "Area has already exists";
        if(donationService.addArea(name)!=null)
            return "Add area success";
        else
            return "Add area failed";
    }
    @PutMapping(value = "/area")
    public String updateArea(@RequestBody DonationPropertiesRequest donationPropertiesRequest)
    {
        if(donationService.updateArea(donationPropertiesRequest.getId(),donationPropertiesRequest.getName())!=null)
            return "Update area success";
        else
            return "Update area failed";
    }
    @DeleteMapping(value = "/area")
    public String deleteArea(@RequestBody String name)
    {
        if(donationService.deleteArea(name)!= 0)
            return "Delete area success";
        else
            return "Delete area failed";
    }
    @PostMapping(value = "/type")
    public String addDonationType(@RequestBody String name)
    {
        if(donationService.isAreaHasNameExists(name))
            return "Donation type has already exists";
        if(donationService.addDonationType(name)!=null)
            return "Add donation type success";
        else
            return "Add donation type failed";
    }
    @PutMapping(value = "/type")
    public String updateDonationType(@RequestBody DonationPropertiesRequest donationPropertiesRequest)
    {
        if(donationService.updateDonationType(donationPropertiesRequest.getId(),donationPropertiesRequest.getName())!=null)
            return "Update donation type success";
        else
            return "Update donation type failed";
    }
    @DeleteMapping(value = "/type")
    public String deleteDonationType(@RequestBody String name)
    {
        if(donationService.deleteDonationType(name)!= 0)
            return "Delete donation type success";
        else
            return "Delete donation type failed";
    }
    @GetMapping(value = "/user")
    public List<UserEntity> getAllUsers(){
        try {
            return userService.getAllUsers();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

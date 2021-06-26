package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.controller.request.DonationPropertiesRequest;
import fpt.se1601.giveandget.controller.request.UpdateRoleRequest;
import fpt.se1601.giveandget.reponsitory.entity.ReportEntity;
import fpt.se1601.giveandget.reponsitory.entity.UserEntity;
import fpt.se1601.giveandget.service.DonationService;
import fpt.se1601.giveandget.service.ReactionService;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    DonationService donationService;
    @Autowired
    UserService userService;
    @Autowired
    ReactionService reactionService;

    @PostMapping(value = "/area")
    public String addArea(@RequestBody String name) {
        if (donationService.isAreaHasNameExists(name))
            return "Area has already exists";
        if (donationService.addArea(name) != null)
            return "Add area success";
        else
            return "Add area failed";
    }

    @PutMapping(value = "/area")
    public String updateArea(@RequestBody DonationPropertiesRequest donationPropertiesRequest) {
        if (donationService.updateArea(donationPropertiesRequest.getId(), donationPropertiesRequest.getName()) != null)
            return "Update area success";
        else
            return "Update area failed";
    }

    @DeleteMapping(value = "/area/{id}")
    public String deleteArea(@PathVariable int id) {
        return donationService.deleteArea(id);

    }

    @PostMapping(value = "/type")
    public String addDonationType(@RequestBody String name) {
        if (donationService.isDonationTypeHasNameExists(name))
            return "Donation type has already exists";
        if (donationService.addDonationType(name) != null)
            return "Add donation type success";
        else
            return "Add donation type failed";
    }

    @PutMapping(value = "/type")
    public String updateDonationType(@RequestBody DonationPropertiesRequest donationPropertiesRequest) {
        if (donationService.updateDonationType(donationPropertiesRequest.getId(), donationPropertiesRequest.getName()) != null)
            return "Update donation type success";
        else
            return "Update donation type failed";
    }

    @DeleteMapping(value = "/type/{id}")
    public String deleteDonationType(@PathVariable int id) {
        return donationService.deleteDonationType(id);

    }

    @GetMapping(value = "/user")
    public List<UserEntity> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(value = "/user")
    public String deleteUser(@RequestBody int userId) {
        try {
            return userService.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return "Delete user failed";
        }
    }

    @PutMapping(value = "/role")
    public String setRoleForUser(@RequestBody UpdateRoleRequest updateRoleRequest) {
        return userService.updateUserRole(updateRoleRequest.getUserId(), updateRoleRequest.getRole());
    }

    @GetMapping(value = "/report")
    public List<ReportEntity> getAllReport() {
        return reactionService.getAllReport();
    }

    @PutMapping(value = "/report/{id}")
    public String replyReport(@PathVariable int id, @RequestBody String content) {
        return reactionService.replyReport(id, content);
    }
}

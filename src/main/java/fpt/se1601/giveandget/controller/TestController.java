package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.controller.request.DonationRequest;
import fpt.se1601.giveandget.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TestController {
    @Autowired
    FileStorageService fileStorageService;
    @PutMapping(value = "/testUpload")
    public String test(@RequestBody DonationRequest donationRequest)
    {
        return "";
    }
}

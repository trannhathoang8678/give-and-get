package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {
    @Autowired
    FileStorageService fileStorageService;

}

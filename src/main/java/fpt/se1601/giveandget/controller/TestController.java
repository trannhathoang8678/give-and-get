package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class TestController {
    @Autowired
    FileStorageService fileStorageService;
    @PostMapping (value = "/testUpload")
    public String test(@RequestParam ("image")MultipartFile image)
    {
        try{
            fileStorageService.deleteFileStartWith("test");
        fileStorageService.save(image,"test");
        return "Add file success";}
        catch (Exception e){
            e.printStackTrace();
            return "Add file failed";
        }
    }
    @PostMapping (value = "/testUploads")
    public String test(@RequestParam ("images")MultipartFile images[])
    {
        try{
            fileStorageService.deleteFileStartWith("test");
            fileStorageService.save(images,"test");
            return "Add file success";}
        catch (Exception e){
            e.printStackTrace();
            return "Add file failed";
        }
    }
}

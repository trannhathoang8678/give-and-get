package fpt.se1601.giveandget;

import fpt.se1601.giveandget.interceptor.GatewayConstant;
import fpt.se1601.giveandget.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.annotation.Resource;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class GiveAndGetApplication {


    public static void main(String[] args) {
        GatewayConstant.addApiEntities();
        FileStorageService.deleteAll();
        FileStorageService.init();
        SpringApplication.run(GiveAndGetApplication.class, args);
    }


}

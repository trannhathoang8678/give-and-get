package fpt.se1601.giveandget;

import fpt.se1601.giveandget.interceptor.GatewayConstant;
import fpt.se1601.giveandget.service.FileStorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GiveAndGetApplication {

    public static void main(String[] args) {
        GatewayConstant.addApiEntities();
        FileStorageService.init();
        SpringApplication.run(GiveAndGetApplication.class, args);
    }


}

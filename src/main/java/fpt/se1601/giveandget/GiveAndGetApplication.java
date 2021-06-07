package fpt.se1601.giveandget;

import fpt.se1601.giveandget.interceptor.GatewayConstant;
import fpt.se1601.giveandget.reponsitory.UserRepository;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GiveAndGetApplication {
    public static void main(String[] args) {
        GatewayConstant.mapRoleApiPath();
        SpringApplication.run(GiveAndGetApplication.class, args);
    }
}

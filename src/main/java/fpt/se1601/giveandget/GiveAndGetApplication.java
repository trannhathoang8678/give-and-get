package fpt.se1601.giveandget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GiveAndGetApplication {

    public static void main(String[] args) {

        SpringApplication.run(GiveAndGetApplication.class, args);
    }

}

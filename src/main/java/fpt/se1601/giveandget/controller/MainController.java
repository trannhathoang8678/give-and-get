package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.interceptor.GatewayConstant;
import fpt.se1601.giveandget.reponsitory.entity.Token;
import fpt.se1601.giveandget.reponsitory.entity.User;
import fpt.se1601.giveandget.service.TokenService;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MainController {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        try {

            if (userService.login(email, password) != null) {
                response.addHeader(GatewayConstant.AUTHORIZATION_HEADER, tokenService.addTokenForUserHasEmail(email));
                return "Login success";
            }
            if (userService.isEmailExists(email))
                return "Password is wrong";
            return "Email is not exist";
        } catch (Exception e) {
            e.printStackTrace();
            return "Login error";
        }
    }

    @PutMapping("/register")
    public String register(@RequestBody User user) {
        try {
            return userService.register(user.getEmail(), user.getPassword(), user.getToken().getToken());
        } catch (Exception e) {
            e.printStackTrace();
            return "Register error";
        }
    }

    @PostMapping("/token")
    public String sendTokenToEmail(String email) {
        try {
            return userService.sendTokenToEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return "Register error";
        }
    }

    @GetMapping("/test")
    public Token getToken() {
        return tokenService.findTokenByEmail("0987654321");
    }
}
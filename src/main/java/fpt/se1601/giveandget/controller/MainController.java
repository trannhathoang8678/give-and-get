package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.interceptor.GatewayConstant;
import fpt.se1601.giveandget.reponsitory.entity.Token;
import fpt.se1601.giveandget.reponsitory.entity.User;
import fpt.se1601.giveandget.service.TokenService;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    public String login(@RequestBody User user, HttpServletResponse response) {
        try {
            if (userService.login(user.getEmail(), user.getPassword()) != null)
            {
                response.addHeader(GatewayConstant.AUTHORIZATION_HEADER, tokenService.createTokenForUserHasEmail(user.getEmail()));
                return "Login success";
            }
            if(userService.isEmailExists(user.getEmail()))
                return "Password is wrong";
            return "Email is not exist";
        } catch (Exception e) {
            e.printStackTrace();
            return "Login error";
        }
    }
    @GetMapping("/register")
    public String register(@RequestBody User user,@RequestBody String token) {
        try {
            if (true)
            {

                return "Register success";
            }
            return "Register failed";
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
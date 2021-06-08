package fpt.se1601.giveandget.controller;

import fpt.se1601.giveandget.interceptor.GatewayConstant;
import fpt.se1601.giveandget.reponsitory.entity.Token;
import fpt.se1601.giveandget.reponsitory.entity.User;
import fpt.se1601.giveandget.service.TokenService;
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
    public ModelAndView getLogin(@RequestBody User user, HttpServletResponse response) {
        response.addHeader(GatewayConstant.AUTHORIZATION_HEADER,"");
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    @GetMapping("/test")
    public Token getToken(){
        return tokenService.findTokenByEmail("0987654321");
    }
}
package ru.personal_coach.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class StartPage {

    @GetMapping("/")
    public String getPage(HttpServletRequest req){
        if (checkToken(req)){
            return "/page/home_page";
        }else {
            return "/page/index";
        }
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/reg")
    public String getRegistrationPage(){
        return "registration";
    }

    private boolean checkToken(HttpServletRequest req) {
        if (req.getCookies() != null && req.getCookies().length > 0) {
            Optional<Cookie> cookieAuth = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equals("Authorization")).findFirst();
            if (cookieAuth.isPresent() && !cookieAuth.get().getValue().isEmpty() ) {
                System.out.println(cookieAuth);
                return true;
            } else {
                cookieAuth.get().setMaxAge(0);
                return false;
            }
        } else
            return false;
    }


}

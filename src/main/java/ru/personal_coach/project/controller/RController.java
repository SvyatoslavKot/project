package ru.personal_coach.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RController {


    @PostMapping(value = "/logining",  produces ="application/json" , consumes = "application/json")
    public String loggining(@RequestBody LoginRequest req){
        System.out.println(req);
        return req.mail;
    }
}

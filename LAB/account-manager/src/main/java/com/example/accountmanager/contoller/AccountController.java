package com.example.accountmanager.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    Environment env;

    @GetMapping("/check")
    public String account(){
        return  String.format("Working on port %s",
                env.getProperty("local.server.port"));
    }
}

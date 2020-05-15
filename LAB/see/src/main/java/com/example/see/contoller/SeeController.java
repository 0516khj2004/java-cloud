package com.example.see.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/see")
public class SeeController {

    @Autowired
    Environment env;


    @GetMapping("/status/check")
    public String account(){
        return  String.format("Working on port %s, secret=%s",
                env.getProperty("local.server.port"),
                env.getProperty("token.secret"));

    }

}

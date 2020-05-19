package com.example.accountmanager.contoller;


import com.example.accountmanager.data.AccountEntity;
import com.example.accountmanager.model.AccountResponseModel;
import com.example.accountmanager.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
public class AccountController {

    @Autowired
    Environment env;

    @Autowired
    AccountService accountService;

    @GetMapping("/account/status/check")
    public String account(){
        return  String.format("Working on port %s, secret=%s",
                env.getProperty("local.server.port"),
                env.getProperty("token.secret"));

    }

    @GetMapping( value = "/users/{id}/account",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            })
    public List<AccountResponseModel> userAccount(@PathVariable String id){
        List<AccountResponseModel> returnValue = new ArrayList<>();

        List<AccountEntity> accountEntities = accountService.getAccount(id);
        if(accountEntities == null || accountEntities.isEmpty()){
            return returnValue;
        }
        Type listType = new TypeToken<List<AccountResponseModel>>(){}.getType();

        returnValue = new ModelMapper().map(accountEntities, listType);
        log.info("Returning " + returnValue.size() + " account");
        return returnValue;
    }


}

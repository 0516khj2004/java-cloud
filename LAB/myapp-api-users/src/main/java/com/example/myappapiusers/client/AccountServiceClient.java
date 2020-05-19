package com.example.myappapiusers.client;

import com.example.myappapiusers.model.AccountResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@FeignClient(name = "account-ws", fallback = AccountFallback.class)
public interface AccountServiceClient {
    @GetMapping("/users/{id}/account")
    List<AccountResponseModel> getAccount(@PathVariable String id);
}


@Component
class AccountFallback implements AccountServiceClient{

    @Override
    public List<AccountResponseModel> getAccount(String id) {
        return new ArrayList<>();
    }
}

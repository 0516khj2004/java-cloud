package com.example.accountmanager.service;

import com.example.accountmanager.data.AccountEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Override
    public List<AccountEntity> getAccount(String userId) {
        List<AccountEntity> value = new ArrayList<>();

        AccountEntity entity = new AccountEntity();
        entity.setUserId(userId);
        entity.setAccountId("accountId");
        entity.setDescription("account 1");
        entity.setId(1L);
        entity.setName("account 1 name");

        AccountEntity entity2 = new AccountEntity();
        entity2.setUserId(userId);
        entity2.setAccountId("accountId2");
        entity2.setDescription("account 2");
        entity2.setId(2L);
        entity2.setName("account 2 name");

        value.add(entity);
        value.add(entity2);

        return value;

    }
}

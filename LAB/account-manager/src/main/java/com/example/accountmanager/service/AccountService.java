package com.example.accountmanager.service;

import com.example.accountmanager.data.AccountEntity;

import java.util.List;

public interface AccountService {
    List<AccountEntity> getAccount(String userId);
}

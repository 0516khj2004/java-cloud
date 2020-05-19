package com.example.accountmanager.data;

import lombok.Data;

@Data
public class AccountEntity {
    private Long id;
    private String accountId;
    private String userId;
    private String name;
    private String description;
}

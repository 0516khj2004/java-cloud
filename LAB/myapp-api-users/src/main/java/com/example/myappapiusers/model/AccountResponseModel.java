package com.example.myappapiusers.model;

import lombok.Data;

@Data
public class AccountResponseModel {
    private String accountId;
    private String userId;
    private String name;
    private String description;
}

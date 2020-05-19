package com.example.myappapiusers.model;

import lombok.Data;

import java.util.List;

@Data

public class UserResponseModel {
    private String userId;
    private  String firstName;
    private  String lastName;
    private  String email;

    //앨범에서 가져옴
    private List<AlbumResponseModel> albums;
    private List<AccountResponseModel> account;
}

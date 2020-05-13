package com.example.myappapiusers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateResponseModel {
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String userId;
}

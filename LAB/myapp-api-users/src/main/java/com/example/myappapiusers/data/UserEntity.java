package com.example.myappapiusers.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class UserEntity  implements Serializable {

    @Id @GeneratedValue
    private  Long id;

    @Column(nullable = false , length = 50) // null 허용 X
    private String firstName;
    @Column(nullable = false , length = 50) // null 허용 X
    private String lastName;
    @Column(nullable = false , length = 120, unique = true) // null 허용 X
    private String email;
    @Column(nullable = false , unique = true) // null 허용 X
    private String userId;
    @Column(nullable = false , unique = true) // null 허용 X
    private String encryptedPassword;
}

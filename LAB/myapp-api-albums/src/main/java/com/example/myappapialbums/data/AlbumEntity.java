package com.example.myappapialbums.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "albums")
public class AlbumEntity  {

    @Id @GeneratedValue
    private  Long id;

    private String albumId;
    private String userId;
    private String name;
    private String description;
}

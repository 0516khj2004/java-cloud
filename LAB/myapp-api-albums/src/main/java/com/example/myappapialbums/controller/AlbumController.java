package com.example.myappapialbums.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumController {
    @GetMapping("/users/{id}/albums")
    public String userAlbums(@RequestBody Long id){
        return null;
    }
}

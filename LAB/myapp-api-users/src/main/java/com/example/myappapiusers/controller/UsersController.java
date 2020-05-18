package com.example.myappapiusers.controller;

import com.example.myappapiusers.model.CreateResponseModel;
import com.example.myappapiusers.model.CreateUserRequestModel;
import com.example.myappapiusers.model.UserResponseModel;
import com.example.myappapiusers.service.UserService;
import com.example.myappapiusers.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    Environment env;

    @Autowired
    UserService userService;

    @GetMapping("/status/check")
    public String status(){
        return String.format("Working on port %s, secret=%s",
                env.getProperty("local.server.port"),
                env.getProperty("token.secret"));
    }

    //사용자 추가 API
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                  produces ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<CreateResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails){
        //model -> UserDto
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(
                MatchingStrategies.STRICT
        );
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createDto = userService.createUser(userDto);

//        return  new ResponseEntity(HttpStatus.CREATED);
        CreateResponseModel returnValue = modelMapper.map(createDto,
                CreateResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping(value = "/{userId}",
                produces = {MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId){
        UserDto userDto = userService.getUserByUserId(userId);
        UserResponseModel returnValue = new ModelMapper().map(userDto,UserResponseModel.class );
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

}

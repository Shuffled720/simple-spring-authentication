package com.vishal.learning.controllers;


import com.vishal.learning.advice.ApiResponse;
import com.vishal.learning.dto.SignupDTO;
import com.vishal.learning.dto.UserDTO;
import com.vishal.learning.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<UserDTO> signUp(@RequestBody SignupDTO signupDTO){
        UserDTO userDTO = authService.signUp(signupDTO);

            return new ApiResponse<>("user created successfully", userDTO, HttpStatus.ACCEPTED);


    }

    @GetMapping("/s")
    public ApiResponse<String> testAuth(){
        ApiResponse<String> response =  new ApiResponse<String>("hi","hi", HttpStatus.ACCEPTED);
        response.setMessage("hi");
        response.setTimestamp(LocalDateTime.now());

        System.out.println(response.getMessage());
        return response;

    }
}

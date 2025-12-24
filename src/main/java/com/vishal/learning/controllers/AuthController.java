package com.vishal.learning.controllers;


import com.vishal.learning.advice.ApiResponse;
import com.vishal.learning.dto.LoginDto;
import com.vishal.learning.dto.LoginResponseDto;
import com.vishal.learning.dto.SignupDTO;
import com.vishal.learning.dto.UserDTO;
import com.vishal.learning.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserDTO>> signUp(@RequestBody SignupDTO signupDTO){
        UserDTO userDTO = authService.signUp(signupDTO);

            ApiResponse<UserDTO> res  = new ApiResponse<>("user created successfully", userDTO, HttpStatus.ACCEPTED);
            return new ResponseEntity<>(res,HttpStatus.ACCEPTED);


    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response){
        LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.accessToken());

        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        ApiResponse<LoginResponseDto> res = new ApiResponse<>("user logged i nsuccessfully", loginResponseDto, HttpStatus.ACCEPTED);

        return new ResponseEntity(res,HttpStatus.ACCEPTED);

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

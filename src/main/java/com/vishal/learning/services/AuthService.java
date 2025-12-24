package com.vishal.learning.services;

import com.vishal.learning.dto.LoginDto;
import com.vishal.learning.dto.LoginResponseDto;
import com.vishal.learning.dto.SignupDTO;
import com.vishal.learning.dto.UserDTO;

public interface AuthService {
    public UserDTO signUp(SignupDTO signUpDto);

    public LoginResponseDto login(LoginDto loginDto);

}

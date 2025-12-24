package com.vishal.learning.services;

import com.vishal.learning.dto.LoginDto;
import com.vishal.learning.dto.LoginResponseDto;
import com.vishal.learning.dto.SignupDTO;
import com.vishal.learning.dto.UserDTO;
import com.vishal.learning.entities.User;
import com.vishal.learning.mapper.UserMapper;
import com.vishal.learning.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;






    @Transactional
    @Override
    public UserDTO signUp(SignupDTO signUpDto) {
        Optional<User> user = userRepository.findByEmail(signUpDto.email());
        if(user.isPresent()){
            throw new BadCredentialsException("User with email: " + signUpDto.email() + "already exists");
        }

        User newUser = userMapper.signupDtoToUser(signUpDto);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(newUser);


        return userMapper.uerToUserDTO(newUser);

    }

    @Override
        public LoginResponseDto login(LoginDto loginDto) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtService.generateAccessToken(user);

            return new LoginResponseDto(user.getId(), accessToken);
        }
}


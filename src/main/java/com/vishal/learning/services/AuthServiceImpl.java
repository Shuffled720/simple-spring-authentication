package com.vishal.learning.services;

import com.vishal.learning.dto.SignupDTO;
import com.vishal.learning.dto.UserDTO;
import com.vishal.learning.entities.User;
import com.vishal.learning.mapper.UserMapper;
import com.vishal.learning.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;






    @Transactional
    @Override
    public UserDTO signUp(SignupDTO signUpDto) {
        Optional<User> user = userRepository.findByEmail(signUpDto.email());
        if(user.isPresent()){
            throw new RuntimeException("User with email: " + signUpDto.email() + "already exists");
        }

        User newUser = userMapper.signupDtoToUser(signUpDto);
        System.out.println(newUser.getEmail());


        userRepository.save(newUser);

        UserDTO userDTO = userMapper.uerToUserDTO(newUser);


        System.out.println(userDTO.name()+"hihihi");
        return userDTO;

    }
}

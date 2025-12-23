package com.vishal.learning.mapper;

import com.vishal.learning.dto.SignupDTO;
import com.vishal.learning.dto.UserDTO;
import com.vishal.learning.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User signupDtoToUser(SignupDTO signupDTO);

    UserDTO uerToUserDTO(User user);
}

package com.example.bromessenger.model.mapper;

import com.example.bromessenger.model.User;
import com.example.bromessenger.model.dto.UserAuthDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User EditDtoToModel(UserAuthDto userDto);
    UserAuthDto EditModelToDto(User user);

}

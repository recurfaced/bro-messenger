package com.example.bromessenger.model.mapper;

import com.example.bromessenger.model.User;
import com.example.bromessenger.model.dto.UserAuthDto;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;
@Service
public class UserMapperImpl implements UserMapper{
    @Override
    public User EditDtoToModel(UserAuthDto userDto) {
        return (userDto != null) ?
                User.builder()
                        .username(userDto.getUsername())
                        .build():
                null;
    }

    @Override
    public UserAuthDto EditModelToDto(User user) {
        requireNonNull(user, "user cannot be null");
        return UserAuthDto.builder()
                .username(user.getUsername())
                .friends(user.getFriends().size())
                .last_login(user.getLastLogin())
                .build();
    }
}

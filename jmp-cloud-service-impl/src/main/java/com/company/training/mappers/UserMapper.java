package com.company.training.mappers;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.company.training.User;
import com.company.training.UserRequestDto;
import com.company.training.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserRequestDto dto);
    UserResponseDto userToUserDto(User user);

    default void updateUserFromDto(UserRequestDto dto, @MappingTarget User user) {
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getSurname() != null) {
            user.setSurname(dto.getSurname());
        }
        if (dto.getBirthday() != null) {
            user.setBirthday(LocalDate.parse(dto.getBirthday()));
        }
    }
}
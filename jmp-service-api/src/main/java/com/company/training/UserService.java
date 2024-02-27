package com.company.training;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(UserRequestDto userRequestDto);

    void deleteUser(Long id);

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUsers();
}

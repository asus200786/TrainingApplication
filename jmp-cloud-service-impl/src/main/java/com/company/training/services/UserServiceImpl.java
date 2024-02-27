package com.company.training.services;

import java.util.List;
import java.util.stream.Collectors;

import com.company.training.User;
import com.company.training.UserRequestDto;
import com.company.training.UserResponseDto;
import com.company.training.UserService;
import com.company.training.mappers.UserMapper;
import com.company.training.repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userMapper.userDtoToUser(userRequestDto);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        User user = userRepository.findById(userRequestDto.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUserFromDto(userRequestDto, user);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }
}
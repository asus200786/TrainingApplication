package com.company.training.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.training.UserRequestDto;
import com.company.training.UserResponseDto;
import com.company.training.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "User Management")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @ApiOperation(value = "Creates a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 400, message = "Invalid input data")})
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
            @ApiParam(value = "User object to create.", required = true) @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto user = userService.createUser(userRequestDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates an existing user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User updated successfully")})
    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(
            @ApiParam(value = "User object to update.", required = true) @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto user = userService.updateUser(userRequestDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a user")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "User deleted successfully")})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(
            @ApiParam(value = "ID of the user to be deleted", required = true) @PathVariable Long id) {

        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get details of a user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User retrieved successfully")})
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(
            @ApiParam(value = "ID of the user to retrieve", required = true) @PathVariable Long id) {

        UserResponseDto user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Get details of all users")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users retrieved successfully")})
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
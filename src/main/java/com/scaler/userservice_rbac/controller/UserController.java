package com.scaler.userservice_rbac.controller;

import com.scaler.userservice_rbac.dto.UserDto;
import com.scaler.userservice_rbac.exceptions.ResourceNotFoundException;
import com.scaler.userservice_rbac.exceptions.UserAlreadyExistInSystem;
import com.scaler.userservice_rbac.models.User;
import com.scaler.userservice_rbac.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDTO) throws UserAlreadyExistInSystem, ResourceNotFoundException {
        User createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByUsername(@PathVariable Long id) throws ResourceNotFoundException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/roles/{roleName}")
    public ResponseEntity<String> assignRole(@PathVariable Long userId, @PathVariable String roleName) throws ResourceNotFoundException {
        userService.assignRoleToUser(userId, roleName);
        return ResponseEntity.ok("Role '" + roleName + "' assigned to User ID " + userId);
    }
}


package com.scaler.userservice_rbac.controller;

import com.scaler.userservice_rbac.dto.UserDto;
import com.scaler.userservice_rbac.exceptions.ResourceNotFoundException;
import com.scaler.userservice_rbac.exceptions.UserAlreadyExistInSystem;
import com.scaler.userservice_rbac.models.User;
import com.scaler.userservice_rbac.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDTO) throws UserAlreadyExistInSystem, ResourceNotFoundException {
        User createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserByUserId(@PathVariable Long id) throws ResourceNotFoundException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/roles/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> assignRolesToUser(@PathVariable Long userId, @RequestBody List<String> roleNames) throws ResourceNotFoundException {
        userService.assignRolesToUser(userId, roleNames);
        return ResponseEntity.ok("Role '" + roleNames + "' assigned to User ID " + userId);
    }
}


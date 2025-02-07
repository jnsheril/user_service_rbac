package com.scaler.userservice_rbac.service;

import com.scaler.userservice_rbac.dto.UserDto;
import com.scaler.userservice_rbac.exceptions.ResourceNotFoundException;
import com.scaler.userservice_rbac.exceptions.UserAlreadyExistInSystem;
import com.scaler.userservice_rbac.models.Role;
import com.scaler.userservice_rbac.models.User;
import com.scaler.userservice_rbac.repository.RoleRepository;
import com.scaler.userservice_rbac.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Constructor Injection
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser(UserDto userDto) throws UserAlreadyExistInSystem, ResourceNotFoundException {
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()){
            throw new UserAlreadyExistInSystem("User Exist");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        // encrypt password
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        // set role
        Set<Role> roles = new HashSet<>();
        for (String roleName : userDto.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
            roles.add(role);
        }
        user.setRoles(roles);

        return userRepository.save(user);

    }

    public User getUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public void assignRoleToUser(Long userId, String roleName) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        //checking avoids unnecessary DB operations
        if (user.getRoles().add(role)) {
            userRepository.save(user);
            System.out.println("Role " + roleName + " assigned to user " + userId);
        } else {
            System.out.println("User " + userId + " already has role " + roleName);
        }
    }
}




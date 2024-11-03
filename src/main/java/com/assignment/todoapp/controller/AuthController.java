package com.assignment.todoapp.controller;
import com.assignment.todoapp.dto.UserLoginDTO;
import com.assignment.todoapp.dto.UserRegistrationDTO;
import com.assignment.todoapp.dto.UserResponseDTO;
import com.assignment.todoapp.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserDetailService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegistrationDTO userDto) {
        UserResponseDTO registeredUser = authService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO loginDto) {
        String token = authService.loginUser(loginDto);
        return ResponseEntity.ok(token);
    }
}

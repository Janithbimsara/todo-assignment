package com.assignment.todoapp.service;

import com.assignment.todoapp.dto.UserLoginDTO;
import com.assignment.todoapp.dto.UserRegistrationDTO;
import com.assignment.todoapp.dto.UserResponseDTO;
import com.assignment.todoapp.entity.User;
import com.assignment.todoapp.repository.UserRepository;
import com.assignment.todoapp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserResponseDTO registerUser(UserRegistrationDTO userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User registeredUser = userRepository.save(user);
        return new UserResponseDTO(registeredUser.getId(), registeredUser.getEmail());
    }

    public String loginUser(UserLoginDTO loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return jwtUtil.generateToken(loginDto.getEmail());
    }
}

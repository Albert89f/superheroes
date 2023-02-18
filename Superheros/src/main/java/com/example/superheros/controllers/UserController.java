package com.example.superheros.controllers;

import com.example.superheros.dtos.LoginDto;
import com.example.superheros.dtos.TokenDto;
import com.example.superheros.services.UserService;
import com.example.superheros.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    final
    AuthenticationManager authenticationManager;

    final
    UserDetailsService userDetailsService;

    final
    JwtUtil jwtTokenUtil;

    final UserService userService;

    public UserController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }
    

    @PutMapping("/user/signup")
    public ResponseEntity<?> signup(@RequestBody LoginDto userSignup) {
        userService.createNewUser(userSignup.getUsername(), userSignup.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginDto userLogin) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userLogin.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new TokenDto(token));
    }
}

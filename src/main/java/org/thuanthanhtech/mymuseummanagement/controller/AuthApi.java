package org.thuanthanhtech.mymuseummanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thuanthanhtech.mymuseummanagement.auth.CustomUserDetails;
import org.thuanthanhtech.mymuseummanagement.auth.LoginRequest;
import org.thuanthanhtech.mymuseummanagement.auth.LoginResponse;
import org.thuanthanhtech.mymuseummanagement.dto.SignUpDto;
import org.thuanthanhtech.mymuseummanagement.config.JwtTokenProvider;
import org.thuanthanhtech.mymuseummanagement.service.impl.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Scope("session")
@RestController
@RequestMapping("/api")
public class AuthApi {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        try {
            userService.registerUser(signUpDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("Logout successfully!",HttpStatus.OK);
    }
}
package org.thuanthanhtech.mymuseummanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thuanthanhtech.mymuseummanagement.auth.CustomUserDetails;
import org.thuanthanhtech.mymuseummanagement.dto.SignUpDto;
import org.thuanthanhtech.mymuseummanagement.entity.User;
import org.thuanthanhtech.mymuseummanagement.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(user);
    }

    public SignUpDto registerUser(SignUpDto signUpDto) throws Exception {
            // add check for username exists in a DB
            if(userRepository.existsByUsername(signUpDto.getUsername())){
               throw new Exception("Username is already taken!");
            }
            // create user object
            User user = new User();
            user.setName(signUpDto.getName());
            user.setUsername(signUpDto.getUsername());
            user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
            userRepository.save(user);
            return signUpDto;
    }

}
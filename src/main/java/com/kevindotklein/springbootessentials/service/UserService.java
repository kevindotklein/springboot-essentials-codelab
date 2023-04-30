package com.kevindotklein.springbootessentials.service;

import com.kevindotklein.springbootessentials.domain.User;
import com.kevindotklein.springbootessentials.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService /*implements UserDetailsService*/ {

    private final UserRepository userRepository;

    /*
    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(this.userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
     */

}

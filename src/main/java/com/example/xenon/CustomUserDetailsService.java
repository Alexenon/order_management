package com.example.xenon;

import com.example.xenon.user.User;
import com.example.xenon.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username)
                .map(this::getUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist"));
    }

    private UserDetails getUserDetails(User user) {
        return new UserDetails() {
            public String getUsername() {
                return user.getUsername();
            }

            public String getPassword() {
                return user.getPassword();
            }

            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }
        };
    }

}
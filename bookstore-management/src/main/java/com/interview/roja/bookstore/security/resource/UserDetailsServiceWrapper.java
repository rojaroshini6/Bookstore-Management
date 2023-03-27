package com.interview.roja.bookstore.security.resource;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceWrapper implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {
        return new User("roja", "roja@123",
                new ArrayList<>());
    }
}

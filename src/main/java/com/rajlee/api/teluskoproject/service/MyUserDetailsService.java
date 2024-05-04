package com.rajlee.api.teluskoproject.service;

import com.rajlee.api.teluskoproject.models.User;
import com.rajlee.api.teluskoproject.models.UserDetailsImpl;
import com.rajlee.api.teluskoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);
        if(user==null){
            System.out.println("Error 404 not fount");
            throw new UsernameNotFoundException("Not found user 404 Error");
        }
        return new UserDetailsImpl(user);
    }
}

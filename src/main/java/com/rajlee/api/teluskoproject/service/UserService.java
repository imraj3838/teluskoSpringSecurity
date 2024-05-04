package com.rajlee.api.teluskoproject.service;

import com.rajlee.api.teluskoproject.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> getAllUser();
    public void registerUser( User user);
}

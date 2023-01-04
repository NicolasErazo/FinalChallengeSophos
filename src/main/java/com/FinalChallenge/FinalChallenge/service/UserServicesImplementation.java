package com.FinalChallenge.FinalChallenge.service;

import com.FinalChallenge.FinalChallenge.entity.User;
import com.FinalChallenge.FinalChallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesImplementation implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getClientById(int id) {
        return userRepository.findById(id);
    }
}

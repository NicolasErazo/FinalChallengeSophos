package com.FinalChallenge.FinalChallenge.service;

import com.FinalChallenge.FinalChallenge.entity.Clients;
import com.FinalChallenge.FinalChallenge.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    public Optional<User> getClientById(int id);

    public List<User> getAllUsers();
}

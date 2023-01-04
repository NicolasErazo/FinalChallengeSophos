package com.FinalChallenge.FinalChallenge.controller;

import com.FinalChallenge.FinalChallenge.entity.Clients;
import com.FinalChallenge.FinalChallenge.entity.User;
import com.FinalChallenge.FinalChallenge.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping("/{id}")
    public ResponseEntity<User> getClientById(@PathVariable("id") int id) {
        return userServices.getClientById(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
    }
}

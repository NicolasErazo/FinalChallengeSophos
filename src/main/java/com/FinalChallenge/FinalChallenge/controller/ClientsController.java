package com.FinalChallenge.FinalChallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FinalChallenge.FinalChallenge.entity.Clients;
import com.FinalChallenge.FinalChallenge.service.ClientsServices;
import com.FinalChallenge.FinalChallenge.service.ProductsServices;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    ClientsServices clientsServices;

    @Autowired
    ProductsServices productsServices;
    

    @GetMapping
    public ResponseEntity<List<Clients>> getClients(){
        return new ResponseEntity<>(clientsServices.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clients> getClientById(@PathVariable("id") int id){
        return clientsServices.getClientById(id)
        .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
        .orElse(new ResponseEntity<Clients>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping
    public ResponseEntity<Clients> createClient(@RequestBody Clients client){
        return new ResponseEntity<>(clientsServices.createClient(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClientById(@PathVariable("id") int id){
        if (clientsServices.deleteClientById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

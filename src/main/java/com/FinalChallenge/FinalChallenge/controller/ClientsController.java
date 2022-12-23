package com.FinalChallenge.FinalChallenge.controller;

import java.util.List;

import com.FinalChallenge.FinalChallenge.entity.Products;
import com.FinalChallenge.FinalChallenge.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FinalChallenge.FinalChallenge.entity.Clients;
import com.FinalChallenge.FinalChallenge.service.ClientsServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    ClientsServices clientsServices;

    @Autowired
    ClientsRepository clientsRepository;

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

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Products>> getAllProductsByClient(@PathVariable("id") int id){
        Clients clients = clientsRepository.findById(id).orElseThrow();

        if(clients != null){
            return new ResponseEntity<>(clients.getProducts(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/products/{idProduct}")
    public ResponseEntity<Products> getProductById(@PathVariable("idProduct") int idProduct){
        return clientsServices.getProductById(idProduct)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/products/add")
    public ResponseEntity<Boolean> addProductByClient(@RequestBody Products product, @PathVariable("id") int id){

        return new ResponseEntity<>(clientsServices.addProductToClient(product, id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Clients> createClient(@RequestBody Clients client){
        if (clientsServices.createClient(client) != null){
            return new ResponseEntity<>(clientsServices.createClient(client), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

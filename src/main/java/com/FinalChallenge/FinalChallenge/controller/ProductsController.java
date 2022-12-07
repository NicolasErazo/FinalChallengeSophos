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

import com.FinalChallenge.FinalChallenge.entity.Products;
import com.FinalChallenge.FinalChallenge.service.ProductsServices;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    ProductsServices productsServices;
    
    @GetMapping
    public ResponseEntity<List<Products>> getClients(){
        return new ResponseEntity<>(productsServices.getAllProducts(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Products> getClientById(@PathVariable("id") int id){
        return productsServices.getProductById(id)
        .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
        .orElse(new ResponseEntity<Products>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping
    public ResponseEntity<Boolean> createClient(@RequestBody Products product){
        return new ResponseEntity<>(productsServices.createProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClientById(@PathVariable("id") int id){
        if (productsServices.deleteProductById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

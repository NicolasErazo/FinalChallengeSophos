package com.FinalChallenge.FinalChallenge.controller;

import com.FinalChallenge.FinalChallenge.entity.Products;
import com.FinalChallenge.FinalChallenge.service.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsServices productsServices;

    @GetMapping
    public ResponseEntity<List<Products>> getClients() {
        return new ResponseEntity<>(productsServices.getAllProducts(), HttpStatus.OK);
    }
}

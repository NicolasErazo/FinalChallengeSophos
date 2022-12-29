package com.FinalChallenge.FinalChallenge.controller;

import com.FinalChallenge.FinalChallenge.entity.Products;
import com.FinalChallenge.FinalChallenge.entity.Transactions;
import com.FinalChallenge.FinalChallenge.repository.ProductsRepository;
import com.FinalChallenge.FinalChallenge.service.TransactionsServices;
import com.FinalChallenge.FinalChallenge.service.TransactionsServicesImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    TransactionsServices transactionsServices;
    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/{id}")
    public ResponseEntity<List<Transactions>> getTransactionsById(@PathVariable("id") int id) {
        Products products = productsRepository.findById(id).orElseThrow();

        if (products != null) {
            return new ResponseEntity<>(products.getTransactions(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{idProduct}/add")
    public ResponseEntity createTransaction(@RequestBody Transactions transactions, @PathVariable("idProduct") int id) {

        if (transactionsServices.addMovementToProduct(transactions, id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}

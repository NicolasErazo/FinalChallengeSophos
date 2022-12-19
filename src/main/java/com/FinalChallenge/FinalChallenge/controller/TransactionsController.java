package com.FinalChallenge.FinalChallenge.controller;

import com.FinalChallenge.FinalChallenge.entity.Transactions;
import com.FinalChallenge.FinalChallenge.service.TransactionsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    TransactionsServices transactionsServices;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transactions>> getTransactionsById(@PathVariable("id") int id){
        return new ResponseEntity<>(transactionsServices.getMovementsByProduct(id), HttpStatus.OK);
    }

    @PostMapping("/{idProduct}/add")
    public ResponseEntity<Boolean> createTransaction(@RequestBody Transactions transactions, @PathVariable("idProduct") int id){
        return new ResponseEntity<>(transactionsServices.addMovementToProduct(transactions, id), HttpStatus.OK);
    }


}

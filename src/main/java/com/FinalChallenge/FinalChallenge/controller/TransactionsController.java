package com.FinalChallenge.FinalChallenge.controller;

import com.FinalChallenge.FinalChallenge.entity.Products;
import com.FinalChallenge.FinalChallenge.entity.Transactions;
import com.FinalChallenge.FinalChallenge.repository.ProductsRepository;
import com.FinalChallenge.FinalChallenge.service.TransactionsServices;
import com.FinalChallenge.FinalChallenge.service.TransactionsServicesImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        return new ResponseEntity<>(products.getTransactions(), HttpStatus.OK);

    }

    @PostMapping("/{idProduct}/add")
    public ResponseEntity<Boolean> createTransaction(@RequestBody Transactions transactions, @PathVariable("idProduct") int id) throws ChangeSetPersister.NotFoundException {

        if (Boolean.TRUE.equals(transactionsServices.addMovementToProduct(transactions, id))) {
            return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Boolean.FALSE);

    }

}

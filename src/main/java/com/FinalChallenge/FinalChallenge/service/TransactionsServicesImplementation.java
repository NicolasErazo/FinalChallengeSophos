package com.FinalChallenge.FinalChallenge.service;

import com.FinalChallenge.FinalChallenge.entity.Transactions;
import com.FinalChallenge.FinalChallenge.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TransactionsServicesImplementation implements TransactionsServices{
    @Autowired
    TransactionsRepository transactionsRepository;

    @Override
    public Boolean addMovementToProduct(Transactions transaction) {
        transactionsRepository.save(transaction);
        return true;
    }

    @Override
    public List<Transactions> getMovementsByProduct(int id) {
        return transactionsRepository.findAllById(Collections.singleton(id));
    }
}

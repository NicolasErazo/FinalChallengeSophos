package com.FinalChallenge.FinalChallenge.service;

import com.FinalChallenge.FinalChallenge.entity.Transactions;

import java.util.List;

public interface TransactionsServices {
    Boolean addMovementToProduct(Transactions transactions);

    List<Transactions> getMovementsByProduct(int id);
}

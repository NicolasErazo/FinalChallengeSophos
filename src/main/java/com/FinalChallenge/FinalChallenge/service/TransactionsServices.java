package com.FinalChallenge.FinalChallenge.service;

import com.FinalChallenge.FinalChallenge.entity.Transactions;

import java.util.List;
import java.util.Optional;

public interface TransactionsServices {
    Boolean addMovementToProduct(Transactions transactions, int id);

    Optional <Transactions> getMovementsByProduct(int id);
}

package com.FinalChallenge.FinalChallenge.repository;

import com.FinalChallenge.FinalChallenge.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
}

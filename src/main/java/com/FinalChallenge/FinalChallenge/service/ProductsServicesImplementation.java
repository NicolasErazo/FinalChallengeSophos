package com.FinalChallenge.FinalChallenge.service;

import java.util.List;
import java.util.Optional;

import com.FinalChallenge.FinalChallenge.entity.Status;
import com.FinalChallenge.FinalChallenge.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FinalChallenge.FinalChallenge.entity.AccountType;
import com.FinalChallenge.FinalChallenge.entity.Products;
import com.FinalChallenge.FinalChallenge.repository.ProductsRepository;

@Service
public class ProductsServicesImplementation implements ProductsServices {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    TransactionsServices transactionsServices;

    @Override
    public Boolean createProduct(Products product) {
        
        product.setAccountNumber(generateAccountNumber(product.getAccountType()));
        productsRepository.save(product);
        return true;
    }

    @Override
    public Optional<Products> getProductById(int id) {
        
        return productsRepository.findById(id);
    }

    @Override
    public boolean changeStatusProductById(int id) {
        
        return getProductById(id).map(client ->{
            productsRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean addTransactionToProduct(Transactions transaction, int id) {
        if (productsRepository.findById(id).isPresent()) {
            transaction.setProduct_id(id);
            transactionsServices.addMovementToProduct(transaction, id);
            return true;
        }
        return false;
    }

    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    private long generateAccountNumber(AccountType accountType){
        String bank = "1234567890";
        String string = "";
        int length = 8;
    
        for (int x = 0; x < length; x++) {
            int randomIndex = (int) (Math.random() * 10);
            char randomCharacter = bank.charAt(randomIndex);
            string += randomCharacter;
        }
    
        switch (accountType) {
            case savings_account:
                string = "46" + string.substring(0, 8);
                break;
            case current_account:
                string = "23" + string.substring(0, 8);
                break;
        }
        return Long.parseLong(string);
    }

    
}

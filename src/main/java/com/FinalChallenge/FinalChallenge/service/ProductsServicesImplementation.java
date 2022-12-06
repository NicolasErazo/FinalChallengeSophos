package com.FinalChallenge.FinalChallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FinalChallenge.FinalChallenge.entity.AccountType;
import com.FinalChallenge.FinalChallenge.entity.Products;
import com.FinalChallenge.FinalChallenge.repository.ProductsRepository;

@Service
public class ProductsServicesImplementation implements ProductsServices {

    @Autowired
    ProductsRepository productsRepository;

    @Override
    public Products createProduct(Products product) {
        // TODO Auto-generated method stub
        product.setAccountNumber(generateAccountNumber(product.getAccountType()));
        return productsRepository.save(product);
    }

    @Override
    public List<Products> getAllProducts() {
        // TODO Auto-generated method stub
        return productsRepository.findAll();
    }

    @Override
    public Optional<Products> getProductById(int id) {
        // TODO Auto-generated method stub
        return productsRepository.findById(id);
    }

    @Override
    public boolean deleteProductById(int id) {
        // TODO Auto-generated method stub
        return getProductById(id).map(client ->{
            productsRepository.deleteById(id);
            return true;
        }).orElse(false);
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
            case Savings_Account:
                string = "46" + string.substring(0, 8);
                break;
            case Current_Account:
                string = "23" + string.substring(0, 8);
                break;
        }
        return Long.parseLong(string);
    }

    
}

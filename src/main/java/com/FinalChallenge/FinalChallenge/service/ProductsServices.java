package com.FinalChallenge.FinalChallenge.service;

import java.util.List;
import java.util.Optional;

import com.FinalChallenge.FinalChallenge.entity.Products;

public interface ProductsServices {

    public Boolean createProduct(Products product);
    public Optional<Products> getProductById(int id);
    public boolean changeStatusProductById(int id);
    
}

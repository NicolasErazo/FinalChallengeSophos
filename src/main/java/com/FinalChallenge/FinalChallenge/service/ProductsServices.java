package com.FinalChallenge.FinalChallenge.service;

import java.util.List;
import java.util.Optional;

import com.FinalChallenge.FinalChallenge.entity.Products;

public interface ProductsServices {

    public Products createProduct(Products product);
    public List<Products> getAllProducts();
    public Optional<Products> getProductById(int id);
    public boolean deleteProductById(int id);
    
}

package com.FinalChallenge.FinalChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FinalChallenge.FinalChallenge.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>{
    
}

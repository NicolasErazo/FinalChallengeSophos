package com.FinalChallenge.FinalChallenge.service;

import java.util.List;
import java.util.Optional;

import com.FinalChallenge.FinalChallenge.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FinalChallenge.FinalChallenge.entity.Clients;
import com.FinalChallenge.FinalChallenge.repository.ClientsRepository;

@Service
public class ClientsServicesImplementation implements ClientsServices {

    @Autowired
    ClientsRepository clientsRepository;

    @Autowired
    ProductsServices productsServices;

    @Override
    public Clients createClient(Clients client) {
        
        return clientsRepository.save(client);
    }

    @Override
    public List<Clients> getAllClients() {
        
        return clientsRepository.findAll();
    }

    @Override
    public Optional<Clients> getClientById(int id) {
        
        return clientsRepository.findById(id);
    }

    @Override
    public boolean deleteClientById(int id) {
        
        return getClientById(id).map(client ->{
            clientsRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean addProductToClient(Products product, int id) {

        if (clientsRepository.findById(id).isPresent()) {
            product.setClient_id(id);
            productsServices.createProduct(product);
            return true;
        }
            return false;

    }

}

package com.FinalChallenge.FinalChallenge.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import com.FinalChallenge.FinalChallenge.entity.Products;
import com.FinalChallenge.FinalChallenge.entity.Status;
import com.FinalChallenge.FinalChallenge.repository.ProductsRepository;
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
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public Clients createClient(Clients client) {
        if (isClientOver18Years(client.getDateOfBirth())) {
            return clientsRepository.save(client);
        }
        return null;
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

        return getClientById(id).map(client -> {
            clientsRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addProductToClient(Products product, int id) {

        if (clientsRepository.findById(id).isPresent()) {
            product.setClient_id(id);
            if (product.getStatus() == null) {
                product.setStatus(Status.ACTIVE);
            } else {
                Optional<Products> productExist = productsRepository.findById((int) product.getId());
                if(productExist.isPresent()) {
                    if (product.getStatus().equals(Status.CANCELED) && productExist.get().getBalance() != 0) {
                        return false;
                    }
                    product.setClient_id(id);
                }
            }
            productsServices.createProduct(product);
            return true;
        }
        return false;

    }

    @Override
    public Optional<Products> getProductById(int id) {
        return productsRepository.findById(id);
    }

    private Boolean isClientOver18Years(LocalDate dateOfBirth) {
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        if (age >= 18) {
            return true;
        } else return false;
    }

}

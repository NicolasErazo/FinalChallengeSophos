package com.FinalChallenge.FinalChallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FinalChallenge.FinalChallenge.entity.Clients;
import com.FinalChallenge.FinalChallenge.repository.ClientsRepository;

@Service
public class ClientsServicesImplementation implements ClientsServices {

    @Autowired
    ClientsRepository clientsRepository;

    @Override
    public Clients createClient(Clients client) {
        // TODO Auto-generated method stub
        return clientsRepository.save(client);
    }

    @Override
    public List<Clients> getAllClients() {
        // TODO Auto-generated method stub
        return clientsRepository.findAll();
    }

    @Override
    public Optional<Clients> getClientById(int id) {
        // TODO Auto-generated method stub
        return clientsRepository.findById(id);
    }

    @Override
    public boolean deleteClientById(int id) {
        // TODO Auto-generated method stub
        return getClientById(id).map(client ->{
            clientsRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

}

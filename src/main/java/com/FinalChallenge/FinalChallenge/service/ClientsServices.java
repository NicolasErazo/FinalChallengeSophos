package com.FinalChallenge.FinalChallenge.service;

import java.util.List;
import java.util.Optional;

import com.FinalChallenge.FinalChallenge.entity.Clients;

public interface ClientsServices {

    public Clients createClient(Clients client);
    public List<Clients> getAllClients();
    public Optional<Clients> getClientById(int id);
    public boolean deleteClientById(int id);
    
}


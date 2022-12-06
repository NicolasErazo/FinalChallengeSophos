package com.FinalChallenge.FinalChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FinalChallenge.FinalChallenge.entity.Clients;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Integer>{

}

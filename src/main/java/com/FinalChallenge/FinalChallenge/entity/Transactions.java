package com.FinalChallenge.FinalChallenge.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime dateOfMovement;

    @Enumerated(EnumType.STRING)
    private TypeOfTransaction typeOfTransaction;

    private String description;
    private long value;
    private String typeOfMovement;
    private long balance;
    private long availableBalance;
    private long product_id;

    @PrePersist
    public void prePersist() {
        dateOfMovement = LocalDateTime.now();
    }
}

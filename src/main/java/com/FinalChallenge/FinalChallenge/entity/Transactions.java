package com.FinalChallenge.FinalChallenge.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String description;

    @NotNull
    @Min(value = 0, message = "The field must be positive")
    private long value;

    private String typeOfMovement;
    private long balance;
    private long availableBalance;
    private long product_id;

    @NotNull
    @Column(updatable = false)
    private String userCreator;

    @PrePersist
    public void prePersist() {
        dateOfMovement = LocalDateTime.now();
    }
}

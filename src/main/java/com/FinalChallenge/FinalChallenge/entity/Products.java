package com.FinalChallenge.FinalChallenge.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private AccountType accountType;

    @Column(updatable = false)
    private long accountNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    private long balance;
    private long availableBalance;
    private boolean GMF;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    private long client_id;

    @OneToMany(targetEntity = Transactions.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private List<Transactions> transactions;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void PreUpdate() {
        updatedAt = LocalDateTime.now();
    }

}


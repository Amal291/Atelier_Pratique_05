package com.example.bankingbackend.entities;

import com.example.bankingbackend.enums.operationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date operationDate;
    private double amount;
    @Enumerated(EnumType.STRING)
    private operationType type;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;
}

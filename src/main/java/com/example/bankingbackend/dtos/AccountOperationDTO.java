package com.example.bankingbackend.dtos;

import com.example.bankingbackend.enums.operationType;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private operationType type;
    private String description;
}

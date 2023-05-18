package com.example.bankingbackend.exceptions;

public class BalanceNotSufficientExeption  extends Exception {
    public BalanceNotSufficientExeption(String message) {
        super(message);
    }
}

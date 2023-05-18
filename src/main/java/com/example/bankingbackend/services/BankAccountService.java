package com.example.bankingbackend.services;

import com.example.bankingbackend.dtos.*;
import com.example.bankingbackend.entities.BankAccount;
import com.example.bankingbackend.entities.Customer;
import com.example.bankingbackend.exceptions.BalanceNotSufficientExeption;
import com.example.bankingbackend.exceptions.BankAccountNotFoundException;
import com.example.bankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    public CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException;
    List<CustomerDTO> liseCustomers();
    static BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientExeption;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource,String accountIdDestination, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientExeption;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getcustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getaccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
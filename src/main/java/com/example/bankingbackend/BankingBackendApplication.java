package com.example.bankingbackend;

import com.example.bankingbackend.entities.*;
import com.example.bankingbackend.enums.AccountStatus;
import com.example.bankingbackend.enums.operationType;
import com.example.bankingbackend.repositories.AccountOperationRepository;
import com.example.bankingbackend.repositories.BankAccountRepository;
import com.example.bankingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingBackendApplication.class, args);
    }
   @Bean
   CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository){
        return args -> {
            BankAccount bankAccount =
                    bankAccountRepository.findById("").orElse(null);
            if (bankAccount !=null) {
                System.out.println("***********************");
                System.out.println(bankAccount.getId());
                System.out.println(bankAccount.getBalance());
                System.out.println(bankAccount.getStatus());
                System.out.println(bankAccount.getCreatdAt());
                System.out.println(bankAccount.getCustomer().getName());
                System.out.println(bankAccount.getClass().getSimpleName()); //le nom de la classe de ce compte

                if (bankAccount instanceof CurrentAccount) {
                    System.out.println("OverDraft=>" + ((CurrentAccount) bankAccount).getOverDraft());
                } else if (bankAccount instanceof SavingAccount) {
                    System.out.println("Rate=>" + ((SavingAccount) bankAccount).getInterestRate());
                }

                //historique de ce compte
                bankAccount.getAccountOperations().forEach(op -> {
                    System.out.println("===========================");
                    System.out.print(op.getType());
                    System.out.println(op.getAmount());
                    System.out.println(op.getOperationDate());
                });
            }

        };
    }




    //@Bean
CommandLineRunner start(CustomerRepository customerRepository,
                        BankAccountRepository bankAccountRepository,
                        AccountOperationRepository accountOperationRepository) {
        return args -> {
            Stream.of("Amal", "KURO").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString()); /*sinon il y'aura une erreur puisqu'on declare l'id comme un sring donc on doit l'incrementer manuellement cette ligne va generer a random id */
                currentAccount.setBalance(Math.random()*9000);
                currentAccount.setCreatdAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
            bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*9000);
                savingAccount.setCreatdAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach(acc->{
                for(int i = 0; i<5; i++){
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*12000);
                    accountOperation.setType(Math.random()>0.5? operationType.DEBIT: operationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);


                }

                BankAccount bankAccount =
                        bankAccountRepository.findById("").orElse(null);
                if (bankAccount !=null) {
                    System.out.println("***********************");
                    System.out.println(bankAccount.getId());
                    System.out.println(bankAccount.getBalance());
                    System.out.println(bankAccount.getStatus());
                    System.out.println(bankAccount.getCreatdAt());
                    System.out.println(bankAccount.getCustomer().getName());
                    System.out.println(bankAccount.getClass().getSimpleName()); //le nom de la classe de ce compte

                    if (bankAccount instanceof CurrentAccount) {
                        System.out.println("OverDraft=>" + ((CurrentAccount) bankAccount).getOverDraft());
                    } else if (bankAccount instanceof SavingAccount) {
                        System.out.println("Rate=>" + ((SavingAccount) bankAccount).getInterestRate());
                    }

                    //historique de ce compte
                    bankAccount.getAccountOperations().forEach(op -> {
                        System.out.println("===========================");
                        System.out.print(op.getType());
                        System.out.println(op.getAmount());
                        System.out.println(op.getOperationDate());
                    });
                }







            });


        };
}
}


package com.example.bankingbackend.entities;

import com.example.bankingbackend.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) /*toutes les classes qui vont heriter de cette classe jpa vas les stocker dans une seule table*/
@DiscriminatorColumn(name = "TYPE", length=4, discriminatorType = DiscriminatorType.STRING)

public abstract class BankAccount {
    @Id
    private  String id;
    private double balance;
    private Date creatdAt;
    @Enumerated(EnumType.STRING) /*l'enumerateur dans la bd sera en forme string au lieu d'ordinal 012*/
    private AccountStatus status;
    @ManyToOne /*creation d'une key etrangere onetomany et manytoone*/
    private Customer customer;
    @OneToMany (mappedBy = "bankAccount", fetch =FetchType.LAZY ) //LAZY va ramener les infos sur le compte mais va pas charger en memoire la liste des operations de ce compte cad ne charge que les attributs du compte
    //Eager charge et les infos sur le compte et les operations mais c'est dangereux+prend bcp de memoire
    //LAZY charge à la demande dans la couche service
    private List <AccountOperation> accountOperations;
}

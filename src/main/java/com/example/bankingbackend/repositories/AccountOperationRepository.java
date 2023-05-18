package com.example.bankingbackend.repositories;

import com.example.bankingbackend.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
        List<AccountOperation> findByBankAccountId(String accountId);
        Page<AccountOperation>findByBankAccountId(String accountId, Pageable pageable);

}
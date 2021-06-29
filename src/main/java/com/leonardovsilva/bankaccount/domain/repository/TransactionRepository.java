package com.leonardovsilva.bankaccount.domain.repository;

import com.leonardovsilva.bankaccount.domain.model.BankAccount;
import com.leonardovsilva.bankaccount.domain.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository  extends CrudRepository<Transaction, String> {

    Optional<List<Transaction>> findAllByBankAccount(BankAccount bankAccount);
}

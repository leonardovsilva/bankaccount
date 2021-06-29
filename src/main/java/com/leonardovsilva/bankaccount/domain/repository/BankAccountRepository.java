package com.leonardovsilva.bankaccount.domain.repository;

import com.leonardovsilva.bankaccount.domain.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, String> {

}

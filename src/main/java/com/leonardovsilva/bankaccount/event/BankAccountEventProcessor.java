package com.leonardovsilva.bankaccount.event;

import com.leonardovsilva.bankaccount.domain.model.BankAccount;
import com.leonardovsilva.bankaccount.domain.model.Transaction;
import com.leonardovsilva.bankaccount.domain.repository.BankAccountRepository;
import com.leonardovsilva.bankaccount.domain.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@ProcessingGroup("amqpEvents")
public class BankAccountEventProcessor {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    @EventHandler
    public void on(BankAccountAddedEvent event) {
        BankAccount bankAccount = bankAccountRepository.save(new BankAccount(event.getId(), event.getDocumentNumber()));
        log.info("A bank account was added! {}", bankAccount );
    }

    @EventHandler
    public void on(AccountTransactionAddedEvent event) {

        var bankAccount = new BankAccount();
        bankAccount.setId(event.getBankAccountId());

        Transaction transaction = transactionRepository.save(new Transaction(event.getId(), event.getOperationType(),
                event.getAmount(), event.getEventDate(), bankAccount));

        log.info("A account transaction was added! {}", transaction );
    }
}

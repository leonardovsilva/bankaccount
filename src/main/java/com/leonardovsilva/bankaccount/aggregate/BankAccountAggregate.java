package com.leonardovsilva.bankaccount.aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import com.leonardovsilva.bankaccount.api.input.AccountTransactionDTO;
import com.leonardovsilva.bankaccount.command.AddAccountTransactionCommand;
import com.leonardovsilva.bankaccount.command.AddBankAccountCommand;
import com.leonardovsilva.bankaccount.domain.enums.OperationsTypes;
import com.leonardovsilva.bankaccount.domain.model.Transaction;
import com.leonardovsilva.bankaccount.event.AccountTransactionAddedEvent;
import com.leonardovsilva.bankaccount.event.BankAccountAddedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Getter
@Aggregate
public class BankAccountAggregate {

    @AggregateIdentifier
    private String id;
    private String documentNumber;
    private List<AccountTransactionAddedEvent> transactions;

    public  BankAccountAggregate(){
        transactions = new ArrayList<>();
    }

    @CommandHandler
    public BankAccountAggregate(AddBankAccountCommand cmd) {
        log.info("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
        Assert.hasLength(cmd.getId(), "Id should not be empty or null.");
        Assert.hasLength(cmd.getDocumentNumber(), "Document Number should not be empty or null.");

        apply(new BankAccountAddedEvent(cmd.getId(), cmd.getDocumentNumber()));
        log.info("Done handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
    }

    @CommandHandler
    public String handle(AddAccountTransactionCommand cmd) {
        log.info("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
        Assert.hasLength(cmd.getId(), "Id should not be empty or null.");
        Assert.notNull(cmd.getOperationType(), "OperationType should not be empty or null.");
        Assert.notNull(cmd.getAmount(), "Amount should not be empty or null.");

        apply(new AccountTransactionAddedEvent(cmd.getId(), cmd.getBankAccountId() ,cmd.getOperationType(),
                cmd.getAmount(), new Date()));

        log.info("Done handling {} command: {}", cmd.getClass().getSimpleName(), cmd);

        return cmd.getId();
    }

    @EventSourcingHandler
    public void on(BankAccountAddedEvent event) {
        log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
        this.id = event.getId();
        this.documentNumber = event.getDocumentNumber();
        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }

    @EventSourcingHandler
    public void on(AccountTransactionAddedEvent event) {
        log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);

        this.transactions.add(event);

        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }
}

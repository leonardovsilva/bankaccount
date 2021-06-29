package com.leonardovsilva.bankaccount.api.controller;

import com.leonardovsilva.bankaccount.api.input.AccountTransactionDTO;
import com.leonardovsilva.bankaccount.command.AddAccountTransactionCommand;
import com.leonardovsilva.bankaccount.domain.enums.OperationsTypes;
import com.leonardovsilva.bankaccount.domain.model.BankAccount;
import com.leonardovsilva.bankaccount.domain.model.Transaction;
import com.leonardovsilva.bankaccount.domain.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class AccountTransactionController {

    private final TransactionRepository repository;
    private final CommandGateway commandGateway;

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getById(@PathVariable String transactionId) {

        var transaction = repository.findById(transactionId);

        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<List<Transaction>> getByAccountId(@PathVariable String accountId) {

        var bankAccount = new BankAccount();
        bankAccount.setId(accountId);
        var transactions = repository.findAllByBankAccount(bankAccount);

        if (transactions.isPresent()) return ResponseEntity.ok(transactions.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<String> create(@RequestBody @Valid AccountTransactionDTO dto) {
        var command = new AddAccountTransactionCommand(dto.getAccountId(), UUID.randomUUID().toString(),
               OperationsTypes.GetValue(dto.getOperationTypeId()), dto.getAmount());
        log.info("Executing command: {}", command);
        return commandGateway.send(command);
    }
}

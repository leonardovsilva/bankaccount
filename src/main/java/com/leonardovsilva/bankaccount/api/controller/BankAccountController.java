package com.leonardovsilva.bankaccount.api.controller;

import com.leonardovsilva.bankaccount.api.input.BankAccountDTO;
import com.leonardovsilva.bankaccount.command.AddBankAccountCommand;
import com.leonardovsilva.bankaccount.domain.model.BankAccount;
import com.leonardovsilva.bankaccount.domain.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class BankAccountController {

    private final BankAccountRepository repository;
    private final CommandGateway commandGateway;

    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccount> getById(@PathVariable String accountId) {

        var bankAccount = repository.findById(accountId);

        return bankAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<String> create(@RequestBody @Valid BankAccountDTO dto) {
        var command = new AddBankAccountCommand(UUID.randomUUID().toString(), dto.getDocumentNumber());
        log.info("Executing command: {}", command);
        return commandGateway.send(command);
    }
}

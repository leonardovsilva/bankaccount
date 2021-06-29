package com.leonardovsilva.bankaccount.command;

import com.leonardovsilva.bankaccount.domain.enums.OperationsTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class AddAccountTransactionCommand {

    @TargetAggregateIdentifier
    private String bankAccountId;

    private String id;

    private OperationsTypes operationType;

    private BigDecimal amount;
}

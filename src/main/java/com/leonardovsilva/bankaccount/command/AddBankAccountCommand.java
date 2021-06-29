package com.leonardovsilva.bankaccount.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@ToString
@AllArgsConstructor
public class AddBankAccountCommand {

    @TargetAggregateIdentifier
    private String id;

    private String documentNumber;
}

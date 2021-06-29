package com.leonardovsilva.bankaccount.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BankAccountAddedEvent {

    private String id;

    private String documentNumber;
}

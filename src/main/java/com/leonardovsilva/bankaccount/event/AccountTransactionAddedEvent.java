package com.leonardovsilva.bankaccount.event;

import com.leonardovsilva.bankaccount.domain.enums.OperationsTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class AccountTransactionAddedEvent {

    private String id;

    private String bankAccountId;

    private OperationsTypes operationType;

    private BigDecimal amount;

    private java.util.Date eventDate;
}

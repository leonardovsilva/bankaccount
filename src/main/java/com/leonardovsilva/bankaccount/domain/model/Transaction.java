package com.leonardovsilva.bankaccount.domain.model;

import com.leonardovsilva.bankaccount.domain.enums.OperationsTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    private String id;

    @Column(name="operation_type_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private OperationsTypes OperationsType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name="event_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date eventDate;

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private BankAccount bankAccount;
}

package com.leonardovsilva.bankaccount.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    private String id;

    @Column(nullable = false)
    private String documentNumber;
}

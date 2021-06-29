package com.leonardovsilva.bankaccount.api.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class BankAccountDTO {

    @NotBlank
    @JsonProperty("document_number")
    private String documentNumber;
}

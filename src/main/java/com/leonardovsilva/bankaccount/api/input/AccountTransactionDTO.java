package com.leonardovsilva.bankaccount.api.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Setter
@Getter
public class AccountTransactionDTO {

    @NotBlank
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("operation_type_id")
    @Min(1)
    @Max(4)
    private int operationTypeId;

    @DecimalMin("0.1")
    private BigDecimal amount;
}

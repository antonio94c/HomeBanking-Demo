package it.bank.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayloadBonifico {

    private String moneyTransferId;
    private String cro;
    private String trn;
    private String status;
    private String uri;
    private String direction;
    private Debtor debtor;
    private Creditor creditor;
    private String feeAccountId;
    private String description;
    private String createdDatetime;
    private String accountedDatetime;
    private String debtorValueDate;
    private String creditorValueDate;
    private Amount amount;
    @JsonProperty("isUrgent")
    private boolean isUrgent;
    @JsonProperty("isInstant")
    private boolean isInstant;
    private String feeType;
    private String[] fees;
    @JsonProperty("hasTaxRelief")
    private boolean hasTaxRelief;

}

package it.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Amount {

    private int debtorAmount;
    private String debtorCurrency;
    private int creditorAmount;
    private String creditorCurrency;
    private String creditorCurrencyDate;
    private int currencyRatio;

}

package it.bank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayloadLeggiSaldo {

    private String date;
    private Double balance;
    private Double availableBalance;
    private String currency;

}

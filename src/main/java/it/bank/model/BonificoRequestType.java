package it.bank.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BonificoRequestType {

    private Creditor creditor;
    private String executionDate;
    private String description;
    private double amount;
    private String currency;
}

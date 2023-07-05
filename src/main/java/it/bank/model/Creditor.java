package it.bank.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Creditor {

    private String name;
    private Account account;

}

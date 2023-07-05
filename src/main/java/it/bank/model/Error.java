package it.bank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Error {

    private String code;
    private String description;
    private String params;

}

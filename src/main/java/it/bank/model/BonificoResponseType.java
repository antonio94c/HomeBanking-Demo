package it.bank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BonificoResponseType extends CommonResponseType{

    private PayloadBonifico payload;

}

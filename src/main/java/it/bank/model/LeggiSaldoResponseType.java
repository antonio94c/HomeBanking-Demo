package it.bank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class LeggiSaldoResponseType extends CommonResponseType{


    private List<Error> error;
    private PayloadLeggiSaldo payload;

}

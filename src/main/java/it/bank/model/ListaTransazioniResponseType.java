package it.bank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ListaTransazioniResponseType extends CommonResponseType{

    private List<Error> error;
    private PayloadTransazioni payload;

}

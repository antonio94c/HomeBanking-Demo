package it.bank;

import it.bank.configuration.PropertiesConf;
import it.bank.service.LeggiSaldoService;
import it.bank.service.ListaTransazioniService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListaTransazioniServiceTest {

    @BeforeAll
    public static void loadProperties(){
        PropertiesConf.loadProperties();
    }

    @Test
    public void testListaTransazioniService() {
        assertEquals(14, callListaTransazioniService());
    }

    private int callListaTransazioniService() {
        return ListaTransazioniService.listaTransazioni();
    }

}

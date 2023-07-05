package it.bank;

import it.bank.configuration.PropertiesConf;
import it.bank.service.LeggiSaldoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeggiSaldoServiceTest {

    @BeforeAll
    public static void loadProperties(){
        PropertiesConf.loadProperties();
    }

    @Test
    public void testLeggiSaldoService() {
        assertEquals(-8.71, callLeggiSaldoService());
    }

    private Double callLeggiSaldoService() {
        return LeggiSaldoService.leggiSaldo();
    }

}
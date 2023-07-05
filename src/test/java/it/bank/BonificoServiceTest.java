package it.bank;

import it.bank.configuration.PropertiesConf;
import it.bank.model.ContextParams;
import it.bank.service.BonificoService;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Setter
@Getter
public class BonificoServiceTest {

    private String beneficiario;
    private double importo;
    private String iban;
    private String causale;

    @BeforeAll
    public static void loadProperties(){
        PropertiesConf.loadProperties();
    }

    public void initMockDataOK(){
        setBeneficiario("Antonio Calabria");
        setImporto(8.500);
        setIban("IT87G0302501601TB8880107212");
        setCausale("Payment invoice");
    }

    @Test
    public void testLeggiSaldoServiceOK() {
        initMockDataOK();
        assertEquals("SC517", callBonificoService().getCode());
    }

    private ContextParams callBonificoService(){
        return BonificoService.bonifico(getBeneficiario(), getImporto(), getIban(), getCausale(), "");
    }

}
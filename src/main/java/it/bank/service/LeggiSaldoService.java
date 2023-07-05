package it.bank.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.bank.configuration.PropertiesConf;
import it.bank.model.LeggiSaldoResponseType;
import it.bank.proxy.RestClient;
import okhttp3.Headers;

import java.text.MessageFormat;

//LeggiSaldoService, retrive balance account
public class LeggiSaldoService extends AbstractService{

    public static Double leggiSaldo() {
        Double result = null;
        try {
            Headers headers = getHeaders();
            String url = buildURL("leggiSaldo");

            //GET request
            String responseBody = RestClient.performGetRequest(url, headers);

            if(responseBody != null && !responseBody.isEmpty()){
                ObjectMapper mapper = new ObjectMapper();

                //Convert JSON response to LeggiSaldoResponseType.class
                LeggiSaldoResponseType responseType = mapper.readValue(responseBody, LeggiSaldoResponseType.class);
                log.debug(responseType);

                //SKIP ERRORS CHECK BECAUSE WE CALL A MOCK SERVICE THAT RETURNS OK

                Double availableBalance = responseType.getPayload().getAvailableBalance();
                result = availableBalance;
                String currency = responseType.getPayload().getCurrency();

                String output = String.format("Available Balance: %.2f %s\n", availableBalance, currency);

                System.out.println(output);
            }else{
                log.warn("Something went wrong");
            }

        }catch (Exception e){
            log.error("Exception during leggi saldo operation {}", e);
        }

        return result;
    }
}

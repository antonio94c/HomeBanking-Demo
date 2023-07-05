package it.bank.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.bank.model.*;
import it.bank.proxy.RestClient;
import okhttp3.Headers;

//BonificoService, call specific service to execute a payment after populated with input data
public class BonificoService extends AbstractService{


    public static ContextParams bonifico(String beneficiario, double importo, String iban, String causale, String executionDate){
        ContextParams contextParams = new ContextParams();
        try{
            Headers headers = getHeaders();
            String url = buildURL("bonifico");

            //build request body to perform post request
            BonificoRequestType bonificoRequestType = prepareBonificoRequestBody(beneficiario, importo, iban, causale, executionDate);

            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(bonificoRequestType);

            log.debug("Request Body: {}", requestBody);

            //POST request
            String responseBody = RestClient.performPostRequest(url, requestBody, headers);

            if(responseBody != null && !responseBody.isEmpty()){
                //Convert JSON response to BonificoResponseType.class
                BonificoResponseType bonificoResponseType = mapper.readValue(responseBody, BonificoResponseType.class);

                //check errors
                if(bonificoResponseType.getErrors() != null && !bonificoResponseType.getErrors().isEmpty()){

                    String code = bonificoResponseType.getErrors().get(0).getCode();
                    String description = bonificoResponseType.getErrors().get(0).getDescription();

                    System.out.println("Bonifico non riuscito!\n");
                    System.out.println(String.format("Code: %s", code));
                    System.out.println(String.format("Description: %s", description));

                    contextParams.setCode(code);
                    contextParams.setDescription(description);

                }else {
                    System.out.println("Bonifico effettuato con successo!");
                    contextParams.setCode("OK");
                }

            }else{
                log.warn("Something went wrong");
            }

        }catch (Exception e){
            log.error("Exception during bonifico operation {}", e);
        }

        return contextParams;
    }

    private static BonificoRequestType prepareBonificoRequestBody(String beneficiario, double importo, String iban, String causale, String executionDate) {
        Account account = new Account(iban, "");
        Creditor creditor = new Creditor(beneficiario, account);
        BonificoRequestType bonificoRequestType = new BonificoRequestType(creditor, executionDate, causale, importo, "EUR");
        return bonificoRequestType;
    }


}

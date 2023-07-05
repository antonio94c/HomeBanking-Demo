package it.bank.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.bank.model.ListaTransazioniResponseType;
import it.bank.model.Transaction;
import it.bank.proxy.RestClient;
import okhttp3.Headers;
import org.apache.http.client.utils.URIBuilder;

import java.util.List;

//ListaTransazioniService, call specific service to retrive transaction data
public class ListaTransazioniService extends AbstractService{

    public static int listaTransazioni() {
        int numTr = 0;
        try{
            Headers headers = getHeaders();

            //Add URL paramter
            URIBuilder uriBuilder = new URIBuilder(buildURL("leggiTransazioni"));
            uriBuilder.addParameter("fromAccountingDate", "2019-01-01");
            uriBuilder.addParameter("toAccountingDate", "2019-12-01");

            String url = uriBuilder.build().toString();

            //GET Request
            String responseBody = RestClient.performGetRequest(url, headers);

            if(responseBody != null && !responseBody.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();

                //Convert JSON response to ListaTransazioniResponseType.class
                ListaTransazioniResponseType responseType = mapper.readValue(responseBody, ListaTransazioniResponseType.class);
                log.debug(responseType);

                //SKIP ERRORS CHECK BECAUSE WE CALL A MOCK SERVICE THAT RETURNS OK

                List<Transaction> transactions = responseType.getPayload().getList();

                //Print result in table form
                printTransactionTable(transactions);

                numTr = transactions != null ? transactions.size() : 0;
            }

        }catch(Exception e) {
            log.error("Exception during leggi saldo operation {}", e);
        }

        return numTr;

    }

    public static void printTransactionTable(List<Transaction> transactions) {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-12s | %-12s | %-8s | %-20s |\n", "Transaction ID", "Value Date", "Amount", "Currency", "Description");
        System.out.println("-----------------------------------------------------------------------------");

        transactions.stream()
                .forEach(transaction -> {
                    System.out.printf("| %-12s | %-12s | %-12s | %-8s | %-20s |\n",
                            transaction.getTransactionId(),
                            transaction.getValueDate(),
                            transaction.getAmount(),
                            transaction.getCurrency(),
                            transaction.getDescription());
                });

        System.out.println("-----------------------------------------------------------------------------");
    }

}

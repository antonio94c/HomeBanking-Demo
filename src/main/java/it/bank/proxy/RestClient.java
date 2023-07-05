package it.bank.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Delegate class to execute API call using OkHttpClient
public class RestClient {

    private static final Logger log = LogManager.getLogger("rest_client");
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    //Peform HTTP get Request
    public static String performGetRequest(String url, Headers headers) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .build();

        String responseBody = "";

        log.info("Perform GET request to {}", url);
        log.trace("Headers {}", headers);

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                log.info("GET request success {}",response.code());
                responseBody = response.body().string();
                log.trace("Response {}", responseBody);
            } else {
                log.error("Error GET request. Response code: {}", response.code());
            }
        }

        return responseBody;
    }


    //Perform HTTP post Request
    public static String performPostRequest(String url, String requestBody, Headers headers) throws Exception {
        RequestBody body = RequestBody.create(requestBody, JSON_MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(body)
                .build();

        String responseBody = "";

        log.info("Perform POST request to {}", url);
        log.trace("RequestBody {}", requestBody);
        log.trace("Headers {}", headers);

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                log.info("POST request success {}",response.code());
                responseBody = response.body().string();
                log.trace("Response {}", responseBody);
            } else {
                log.error("Error POST request. Response code: {}", response.code());
                responseBody = response.body().string();
            }
        }
        return responseBody;
    }

}

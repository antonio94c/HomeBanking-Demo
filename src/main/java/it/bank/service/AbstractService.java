package it.bank.service;

import it.bank.configuration.PropertiesConf;
import okhttp3.Headers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbstractService {

    protected static final Logger log = LogManager.getLogger("services");

    private static final String STR_APIKEY = "apiKey";
    private static final String STR_AUTHSCHEMA = "Auth-Schema";
    private static final String STR_CONTENT_TYPE = "Content-Type";
    private static final String STR_CONTENT_JSON = "application/json";

    //Method used to build mandatory headers
    protected static Headers getHeaders(){
        Headers headers = new Headers.Builder()
                    .add(STR_APIKEY, PropertiesConf.getProperty(STR_APIKEY))
                    .add(STR_AUTHSCHEMA, PropertiesConf.getProperty("authSchema"))
                    .add(STR_CONTENT_TYPE, STR_CONTENT_JSON)
                    .build();

        return headers;
    }

    //Method used to build and validate URl
    protected static String buildURL(String method) {
        String url = "";

        try {
            String urlStr = PropertiesConf.getProperty("host").concat(PropertiesConf.getProperty(method));
            urlStr = urlStr.replace("{accountId}", PropertiesConf.getProperty("accountId"));
            URI absoluteURI = new URI(urlStr); //validate
            url = absoluteURI.toString();
        }catch (URISyntaxException e){
            log.error("Error occured during build URI {}", e);
        }

        return url;
    }

}

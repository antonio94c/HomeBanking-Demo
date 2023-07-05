package it.bank.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* Class to manage properties */
public class PropertiesConf {

    private static final String CONF_PATH = "conf/application.properties";
    private static Properties properties;

    public static void loadProperties() {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(CONF_PATH);
            properties = new Properties();
            properties.load(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static String getProperty(String key) {
        if(properties == null)
            loadProperties();

        return properties.getProperty(key);
    }
}

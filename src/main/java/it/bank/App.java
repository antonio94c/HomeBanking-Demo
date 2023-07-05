package it.bank;

import it.bank.configuration.PropertiesConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class App {

    static Logger logger = LogManager.getLogger(App.class);

    /* Main Class, load conf/application.properties and set up appplication menu */
    public static void main( String[] args ){
        logger.info("Home Banking start");

        PropertiesConf.loadProperties();
        Home.mainMenu();
    }
}

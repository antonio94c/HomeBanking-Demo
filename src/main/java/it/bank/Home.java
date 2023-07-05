package it.bank;

import it.bank.configuration.PropertiesConf;
import it.bank.service.BonificoService;
import it.bank.service.LeggiSaldoService;
import it.bank.service.ListaTransazioniService;
import it.bank.utils.Util;
import it.bank.utils.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
/* Home Class
 Since we don't have a user interface, it acts as the application's menu, reading the operation from the console. */
public class Home {

    private static final Logger log = LogManager.getLogger(Home.class);

    public static void mainMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(String.format("Benvenuto in HomeBanking, N. Conto %s", PropertiesConf.getProperty("accountId")));

        boolean continua = true;
        while (continua) {
            System.out.println("Scegli un'opzione:");
            System.out.println("1. Lettura saldo");
            System.out.println("2. Lista di transazioni (da 2019-01-01 a 2019-12-01)");
            System.out.println("3. Bonifico");
            System.out.println("4. Esci");

            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    log.info("Operation 1 (leggi saldo) - start");
                    LeggiSaldoService.leggiSaldo();
                    break;
                case 2:
                    log.info("Operation 2 (lista transazioni) - start");
                    ListaTransazioniService.listaTransazioni();
                    break;
                case 3:
                    log.info("Operation 3 (bonifico) - start");
                    subMenuBonifico();
                    break;
                case 4:
                    log.info("Operation 4 - Exit from Banking App");
                    continua = false;
                    break;
                default:
                    log.info("Operation {} - Not valid", scelta);
                    System.out.println("Opzione non valida!");
            }
        }
    }

    /* SubMenu for option 3 to retrieve mandatory data to execute payment
    */
    public static void subMenuBonifico(){

        log.info("subMenuBonifico start");

        Scanner scanner = new Scanner(System.in);

        String beneficiario = askDest(scanner);
        double importo = askAmount(scanner);
        String iban = askIban(scanner);
        String causale = askDescr(scanner);
        String executionDate = askExecutionDate(scanner);

        BonificoService.bonifico(beneficiario, importo, iban, causale, executionDate);
    }

    private static String askDest(Scanner scanner){
        log.info("askDest start");
        String beneficiario = "";
        boolean nextIteration = true;
        while (nextIteration){
            System.out.println("Inserisci Beneficiario: ");
            beneficiario = scanner.nextLine();
            log.trace("Beneficiary typed: {}", beneficiario );
            if(beneficiario !=null && !beneficiario.isEmpty()){
                nextIteration = false;
            }else{
                System.out.println("Il beneficiario è oggligatorio");
            }
        }

        return beneficiario;
    }

    private static double askAmount(Scanner scanner){
        log.info("askAmount start");

        double amount = 0.0;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ITALIAN);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

        boolean nextIteration = true;
        while(nextIteration){
            System.out.println("Inserisci importo");
            String importo = scanner.nextLine();
            log.trace("Amount typed: {}", importo);
            try {
                double importoFormatted = decimalFormat.parse(importo).doubleValue();
                if(importoFormatted <= 0.0 ){
                    System.out.println("L'Importo deve essere maggiore di 0");
                }else {
                    nextIteration = false;
                    amount = importoFormatted;
                }
            }catch (ParseException e){
                log.error("ParseException {}", e);
                System.out.println("Importo non valido!");
            }
        }

        return amount;
    }

    public static String askIban(Scanner scanner){
        log.info("askIban start");
        String iban = "";
        boolean nextIteration = true;
        while(nextIteration){
            System.out.println("Inserisci Iban Beneficiario: ");
            iban = scanner.nextLine();
            log.trace("IBAN typed: {}", iban);
            if(!Validator.ibanValidator(iban)){
                System.out.println("Iban non valido!");
            }else{
                nextIteration = false;
            }
        }
        return iban;
    }

    public static String askDescr(Scanner scanner){
        log.info("askDesc start");
        String descr = "";
        boolean nextIteration = true;
        while (nextIteration){
            System.out.println("Inserisci Causale: ");
            descr = scanner.nextLine();
            log.trace("Description typed: {}", descr);
            if(descr !=null && !descr.isEmpty()){
                nextIteration = false;
            }else{
                System.out.println("La causale è oggligatoria");
            }
        }
        return descr;
    }

    public static String askExecutionDate(Scanner scanner){
        log.info("askIban askExecutionDate");
        String patternIn = "dd/MM/yyyy";
        String patternOu = "yyyy-MM-dd";

        String date = "";
        boolean nextIteration = true;
        while (nextIteration){
            System.out.println(String.format("Inserisci Data di esecuzione (%s): ", patternIn));
            String dateIn = scanner.nextLine();
            log.trace("Execution date typed: {}", dateIn);
            if(dateIn !=null && !dateIn.isEmpty()){
                if(Validator.validaData(dateIn, patternIn)){
                    date = Util.convertDate(dateIn, patternIn, patternOu); //Convert date from dd/MM/yyyy to yyyy-MM-dd
                    nextIteration = false;
                }else{
                    System.out.println("Data non valida!");
                }
            }else{
                nextIteration = false;
            }
        }

        return date;
    }
}

package it.bank.utils;

import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.IBANValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//Validator for IBAN and Date
public class Validator {

    public static boolean ibanValidator(String iban){
        IBANValidator ibanValidator = IBANValidator.getInstance();
        return ibanValidator.isValid(iban);
    }

    public static boolean validaData(String data, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);  // Set strict mode for validation

        try {
            sdf.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}

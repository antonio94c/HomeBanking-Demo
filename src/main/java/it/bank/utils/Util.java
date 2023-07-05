package it.bank.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    //Method used to convert a date
    public static String convertDate(String dataInput, String patternIn, String patternOu){
        SimpleDateFormat sdfInput = new SimpleDateFormat(patternIn);
        SimpleDateFormat sdfOutput = new SimpleDateFormat(patternOu);

        try {
            Date date = sdfInput.parse(dataInput);
            return sdfOutput.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}

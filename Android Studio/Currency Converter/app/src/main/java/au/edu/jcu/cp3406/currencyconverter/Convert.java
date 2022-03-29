package au.edu.jcu.cp3406.currencyconverter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Convert {
    // convert SGD user input to the selected foreign currency

    static double convertUSD(double input) {

        return input * 0.73;
    }

    static double convertGBP(double input) {

        return input * 0.55;
    }

    static double convertEUR(double input) {

        return input * 0.65;
    }

    static double convertAUD(double input) {

        return input * 1.03;
    }

    static double convertJPY(double input) {

        return input * 82.57;
    }

    static double convertCHF(double input) {

        return input * 0.67;
    }


    static double round(double result, int decimal) {
        // to round off doubles to specific decimal places
        if (decimal < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(result));
        bd = bd.setScale(decimal, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}

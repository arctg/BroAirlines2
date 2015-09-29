package edu.trainee.logic;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by dennis on 04.06.2015.
 */

/**
 * Setting global prices for
 * @value PRIORITY_BOARDING - Priority bording service
 * @value BAGGAGE - baggage service
 * and returns new Date object
 * @see PriceFixer
 *
 *
 *
 */

public class PriceFixer {
    public static final BigDecimal PRIORITY_BOARDING = BigDecimal.valueOf(20).setScale(2);
    public static final BigDecimal BAGGAGE = BigDecimal.valueOf(15).setScale(2);


    /**
     * genereates price for flight bases on creation date of flight,
     * flight date and number of free places in airplane.
     * @param createDate - creating date of flight
     * @param flightDate - flight date
     * @param numOfPlaces - number of places in airplane
     * @param numOfBusyPlaces - number of busy places
     * @value BASE_COEFFICIENT - coefficient that gives max 25% add price for one service
     *
     *
     * @see PriceFixer
     *
     *
     *
     */

    public static BigDecimal fixer(Calendar createDate, Calendar flightDate, BigDecimal flightPrice, int numOfPlaces, int numOfBusyPlaces) {
        //changing price by dates
        final BigDecimal BASE_COEFICIENT = BigDecimal.valueOf(0.25);
        BigDecimal newPrice = BigDecimal.ZERO;
        BigDecimal dateCoeficient, placeCoeficient;

        Calendar currentDate = CurrentDate.getCurrentDate();

        //System.out.println("Cho" + hoursBetween(createDate.getTime(), flightDate.getTime()));


//        Date date = currentDate.getTime() - createDate.getTime();
        double a = hoursBetween(currentDate.getTime(),createDate.getTime());
        System.out.println("a: " + a);
        double b = hoursBetween(flightDate.getTime(),createDate.getTime());
        System.out.println("b: " + b);
        double c = (a/b);
        System.out.println("C:" + c);

        dateCoeficient = BASE_COEFICIENT.multiply(flightPrice).multiply(BigDecimal.valueOf(c));
        //System.out.println("Date coef: " + dateCoeficient.setScale(2,BigDecimal.ROUND_DOWN));
        placeCoeficient = BASE_COEFICIENT.multiply(flightPrice).multiply(BigDecimal.valueOf(((double) numOfBusyPlaces)/(double) numOfPlaces));
        //System.out.println("Place coef: " + placeCoeficient.setScale(2,BigDecimal.ROUND_DOWN));
        BigDecimal priceCoeficient = dateCoeficient.add(placeCoeficient);

        return priceCoeficient.setScale(2,BigDecimal.ROUND_DOWN);
    }


    public static int hoursBetween(Date d1, Date d2)
    {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60));
    }


}

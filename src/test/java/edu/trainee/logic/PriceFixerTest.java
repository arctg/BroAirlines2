package edu.trainee.logic;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by dennis on 9/26/2015.
 */
public class PriceFixerTest {

    @Test
    public void testFixer() throws Exception {


        Calendar flightDate = new GregorianCalendar();
        try {
            flightDate.setTime(new SimpleDateFormat("yy-MM-dd HH:mm").parse("2015-11-27 03:00"));
        } catch (ParseException e) {
        }

        System.out.println("Hours:" + PriceFixer.hoursBetween(CurrentDate.getCurrentDate().getTime(), flightDate.getTime()));


        Calendar creationDate = new GregorianCalendar();
        creationDate.setTime(new SimpleDateFormat("yy-MM-dd HH:mm").parse("2015-09-25 03:00"));
        BigDecimal flightPrice = BigDecimal.valueOf(200);
        int numOfPlaces = 400;
        int busyPlaces = 399;

        System.out.println(PriceFixer.fixer(creationDate, flightDate, flightPrice, numOfPlaces, busyPlaces));

        creationDate.setTime(new SimpleDateFormat("yy-MM-dd HH:mm").parse("2015-09-20 03:00"));
        System.out.println(PriceFixer.fixer(creationDate, flightDate, flightPrice, numOfPlaces, busyPlaces));

        creationDate.setTime(new SimpleDateFormat("yy-MM-dd HH:mm").parse("2015-09-25 03:00"));
        System.out.println(PriceFixer.fixer(creationDate, flightDate, flightPrice, numOfPlaces, busyPlaces));

        creationDate.setTime(new SimpleDateFormat("yy-MM-dd HH:mm").parse("2015-02-25 03:00"));
        System.out.println(PriceFixer.fixer(creationDate, flightDate, flightPrice, numOfPlaces, busyPlaces));

        creationDate.setTime(new SimpleDateFormat("yy-MM-dd HH:mm").parse("2015-09-27 00:00"));
        System.out.println(PriceFixer.fixer(creationDate, flightDate, flightPrice, numOfPlaces, busyPlaces));


        Calendar time = CurrentDate.getCurrentDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = format.format(CurrentDate.getCurrentDate().getTime());
        System.out.println("Now is:" + now);

        Calendar newTime = CurrentDate.getCurrentDate();
        newTime.setTime(new SimpleDateFormat("yy-MM-dd HH:mm").parse("2015-09-25 03:00"));
        String notNow = format.format(newTime.getTime());
        System.out.println("notNow is: " + notNow);

        time = CurrentDate.getCurrentDate();
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        now = format.format(CurrentDate.getCurrentDate().getTime());
        System.out.println("Now is:" + now);

    }
}
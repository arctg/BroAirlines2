package edu.trainee.logic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by dennis on 07.06.2015.
 */
public class CurrentDate {
    /**
     * Setting global Date
     * and returns new Date object
     * Date
     * <p>
     * You also can "move" Date to future or to past
     * to show how price of flights changing
     * by uncomment below lines and comment line: return new Date();
     */

    public static Calendar getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
        return cal;

//        --------------------------------

//        Calendar cal = new GregorianCalendar();
//        try {
//            cal.setTime(new SimpleDateFormat("yy-MM-dd HH:mm").parse("2015-10-15 00:00"));
//            return cal;
//        } catch (ParseException e) {
//            System.out.println(e);
//            return Calendar.getInstance();
//        }


    }

}

package io.dylan.antisporttery.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date parseToDate(String strDate, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(strDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String formatToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }
}

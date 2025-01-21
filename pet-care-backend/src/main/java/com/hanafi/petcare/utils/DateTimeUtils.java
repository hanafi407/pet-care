package com.hanafi.petcare.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static LocalDate date(String data){
      return   LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static LocalTime time(String data){
        return LocalTime.parse(data, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static String timeNow(){
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
    }

    public static String dateNow(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}

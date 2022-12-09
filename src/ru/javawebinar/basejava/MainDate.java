package ru.javawebinar.basejava;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainDate {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    Date date = new Date();
    System.out.println(date);
    System.out.println(System.currentTimeMillis() - start);

    Calendar cal = Calendar.getInstance();
    cal.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
    System.out.println(cal.getTime());

    LocalDate localDate = LocalDate.now();
    LocalTime localTime = LocalTime.now();
    LocalDateTime ldt = LocalDateTime.of(localDate, localTime);
    System.out.println(ldt);

    SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
    System.out.println(sdf.format(date));

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    System.out.println(dtf.format(ldt));
  }
}

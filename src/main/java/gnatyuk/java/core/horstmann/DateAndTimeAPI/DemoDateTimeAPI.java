package gnatyuk.java.core.horstmann.DateAndTimeAPI;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class DemoDateTimeAPI {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        System.out.println(format.format(zonedDateTime));

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        System.out.println(formatter.format(localDateTime));

        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println(formatter1.format(localDateTime));
        System.out.println(formatter1.withLocale(Locale.GERMANY).format(localDateTime));

        Instant instant = Instant.from(zonedDateTime);
        System.out.println(instant.toString());

        Date date = new Date();
        System.out.println(date.toString());

        instant = date.toInstant();
        System.out.println(instant.toString());

        ZonedDateTime zonedDateTime1 = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(zonedDateTime1);

    }
}

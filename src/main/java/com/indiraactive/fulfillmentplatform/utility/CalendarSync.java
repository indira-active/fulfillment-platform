package com.indiraactive.fulfillmentplatform.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Component
public class CalendarSync {
    private TimeZone timeZone = TimeZone.getTimeZone("US/Eastern");

    public Date getDateFromDateTime(LocalDateTime localDateTime) {
        return Calendar.getInstance().getTime();
    }

    public LocalDateTime getLocalDateTimeFromDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), TimeZone.getTimeZone("US/Eastern").toZoneId()).truncatedTo(ChronoUnit.MINUTES);
    }

    public LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now(timeZone.toZoneId()).truncatedTo(ChronoUnit.MINUTES);
    }

    public Date getDateNow() {
        return getDateFromDateTime(LocalDateTime.now());
    }
}

package com.indiraactive.fulfillmentplatform.utility;

import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Component
public class CalendarSync {
    private TimeZone timeZone = TimeZone.getTimeZone("US/Eastern");

    public Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(timeZone.toZoneId()).toInstant().truncatedTo(ChronoUnit.MINUTES));
    }

    public LocalDateTime getLocalDateTimeFromDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), timeZone.toZoneId()).truncatedTo(ChronoUnit.MINUTES);
    }

    public LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now(timeZone.toZoneId()).truncatedTo(ChronoUnit.MINUTES);
    }

    public Date getDateNow() {
        return getDateFromLocalDateTime(getLocalDateTimeNow());
    }

    public Date getNextDateFromCronSequence(String cronSequence) {
        if(CronSequenceGenerator.isValidExpression(cronSequence)) {
            return new CronSequenceGenerator(cronSequence).next(getDateNow());
        }

        return null;
    }
}

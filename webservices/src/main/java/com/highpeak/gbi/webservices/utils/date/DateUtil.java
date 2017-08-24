package com.highpeak.gbi.webservices.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

/**
 * Utility class which performs common date and Time related operations
 * 
 * @author sandesha, Created on 20/08/17
 */
@Component
public class DateUtil {

    private static final String DATE_FORMAT = "dd-MM-yyyy E";

    private DateUtil()
    {
    }

    /**
     * Get UTC calendar for given milliseconds date with offset
     *
     * @param millisecondsTime
     * @param offsetHour
     * @param offsetMinute
     * @return
     */
    public Calendar getCalendarWithTimeAtEndOfDay( final Long millisecondsTime, final Integer offsetHour,
            final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withTime(23, 59, 59, 999).withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * Get UTC calendar for given milliseconds date with offset
     *
     * @param millisecondsTime
     * @param offsetHour
     * @param offsetMinute
     * @return
     */
    public synchronized Calendar getCalendarWithTimeAtStartOfDay( final Long millisecondsTime, final Integer offsetHour,
            final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * Gives the Calendar object at start-time of the day according to offset and adds/subtracts the
     * number of days passed
     *
     * @param offsetHour
     * @param offsetMinute
     * @param numberOfDays
     * @return
     */
    public synchronized Calendar getCalendarWithTimeAtStartOfDayBasedOnNumberOfDays( final Integer offsetHour,
            final Integer offsetMinute, final Integer numberOfDays )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(currentTimeMillis(), zone).withTimeAtStartOfDay().plusDays(numberOfDays)
                .withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Return current UTC time in milliseconds
     *
     * @return
     */
    public Long currentTimeMillis()
    {
        return new DateTime().withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed
     *
     * @param millisecondsTime
     * @return
     */
    public static synchronized Calendar getUTCCalenderInstance( final Long millisecondsTime )
    {
        return new DateTime(millisecondsTime).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed using provided offset
     *
     * @param millisecondsTime
     * @param offsetHour
     * @param offsetMinute
     * @return calendar
     */
    public synchronized Calendar getUTCCalenderInstanceUsingOffset( final Long millisecondsTime,
            final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Parse given string date with specified format, add specified number of days and return in UTC
     * with time at start of the day in DB_DATE_FORMAT string format
     *
     * @param date
     * @param numberOfDays
     * @return
     */
    public synchronized Calendar getFutureDateStringWithTimeAtStartOfDay( final Long date, final int numberOfDays )
    {

        return new DateTime(date).withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * Get calendar with time at start of day
     *
     * @param calendar
     * @return
     */
    public synchronized Calendar getCalendarWithTimeAtStartOfDay( final Calendar calendar )
    {
        return new DateTime(calendar).withTimeAtStartOfDay().withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     *
     * @param timestamp
     * @return
     */
    public String getStringDate( Long timestamp )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(calendar.getTime());
    }
}

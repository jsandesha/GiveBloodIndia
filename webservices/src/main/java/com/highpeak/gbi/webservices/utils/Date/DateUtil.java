package com.highpeak.gbi.webservices.utils.Date;

import java.util.Calendar;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author sandesha, Created on 20/08/17
 */
public class DateUtil {

    public static final String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * Get UTC calendar for given milliseconds date with offset
     *
     * @param millisecondsTime
     * @param offsetHour
     * @param offsetMinute
     * @return
     */
    public static Calendar getCalendarWithTimeAtEndOfDay(final Long millisecondsTime, final Integer offsetHour,
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
    public static synchronized Calendar getCalendarWithTimeAtStartOfDay( final Long millisecondsTime,
                                                                         final Integer offsetHour, final Integer offsetMinute )
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
    public static synchronized Calendar getCalendarWithTimeAtStartOfDayBasedOnNumberOfDays( final Integer offsetHour,
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
    public static Long currentTimeMillis()
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
    public static synchronized Calendar getUTCCalenderInstanceUsingOffset( final Long millisecondsTime,
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
    public static synchronized Calendar getFutureDateStringWithTimeAtStartOfDay( final Long date,
                                                                                 final int numberOfDays )
    {

        return new DateTime(date).withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * compare passed in dates
     *
     * @param date1
     * @param date2
     * @return returns true if date1 is after date2
     **/
    public static Boolean dateComparator( final Calendar date1, final Calendar date2 )
    {
        return date1.after(date2);
    }
}

package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;

public class DateUtil {

	public static String permasolDateFormat = "yyyy-MM-dd";
	
	public static String dateTimeFormat = "yyyy-MM-dd hh:mm a";
	
	public static Date parseDateTimeFormat(String dateString) {
		try {
			return new SimpleDateFormat(dateTimeFormat).parse(dateString);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getDateWithTimeZone(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String offsetTime = SessionProperty.getUserTimeZoneOffset();
		if(StringUtils.isNotEmpty(offsetTime)) {
			Integer minutes = Integer.parseInt(offsetTime);
			calendar.add(Calendar.MINUTE, minutes);
		}
		return calendar.getTime();
	}

	public static Date getDate(String inputDate) {
		SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = parseFormat.parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getDate(Date inputDate) {
		SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		try {
			date = parseFormat.format(inputDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	

	public static String getDateWithTime(Date inputDate) {
		SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		String date = "";
		try {
			date = parseFormat.format(inputDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
/**
 * @author Lakshmi
 * Utility method to get date without time consideration
 * @param inputDate
 * @return
 */
	public static Calendar getDateWithoutTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		//Logger.info("Parsed date =="+calendar.getTime());
		return calendar;
	}
	
	public static Date getStartDate(Date startDate) {
		Calendar from = Calendar.getInstance();
		from.setTime(startDate);
		from.set(Calendar.HOUR_OF_DAY, 0);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.SECOND, 0);
		from.set(Calendar.MILLISECOND, 0);

		return from.getTime();
	}

	public static Date getEndDate(Date endDate) {
		Calendar to = Calendar.getInstance();
		to.setTime(endDate);
		to.set(Calendar.HOUR_OF_DAY, 23);
		to.set(Calendar.MINUTE, 59);
		to.set(Calendar.SECOND, 59);
		to.set(Calendar.MILLISECOND, 999);

		return to.getTime();
	}

	
	public static Date getFormatedDate(String dateString) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = formatter.parse(dateString);
			return date;
		} catch (Exception e) {
			try {
				Date date = formatter1.parse(dateString);
				return date;
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getFormatedDateString(Date date)
	{
	   String dateString = null;
	   SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
	   try{
		dateString = sdfr.format( date );
	   }catch (Exception ex ){
		  ex.printStackTrace(); 
	   }
	   return dateString;
	}
	
	
	public static Integer getDaysDifference(Date startDate,Date endDate) {
		DateTime startTime = new DateTime(startDate);
		DateTime  endTime = new DateTime(endDate);;
		Period p = new Period(startTime, endTime);
		return p.getDays();
	}
	
	public static Integer getMinuteDifference(Date startDate,Date endDate) {
		DateTime startTime = new DateTime(startDate);
		DateTime  endTime = new DateTime(endDate);;
		Period p = new Period(startTime, endTime);
		return p.getMinutes();
	}
	
	public static Integer getHourDifference(Date startDate,Date endDate) {
		DateTime startTime = new DateTime(startDate);
		DateTime  endTime = new DateTime(endDate);;
		Period p = new Period(startTime, endTime);
		return p.getHours();
	}
	
	public static Integer getSecoundDifference(Date startDate,Date endDate) {
		DateTime startTime = new DateTime(startDate);
		DateTime  endTime = new DateTime(endDate);;
		Period p = new Period(startTime, endTime);
		return p.getSeconds();
	}
	
	public static Integer getMilisecoundDifference(Date startDate,Date endDate) {
		DateTime startTime = new DateTime(startDate);
		DateTime  endTime = new DateTime(endDate);;
		Period p = new Period(startTime, endTime);
		return p.getMillis();
	}
	
	
	
	/**
	 * @author Lakshmi
	 * Action to count No.of.Days b/w 2 dates
	 */
	public static Long getDateDifferenceInDays(Date fromDate,Date toDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.add(Calendar.DATE, -1);
		    long diff = toDate.getTime() - cal.getTime().getTime();
		    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	/**
	 * @author Lakshmi
	 *  Utility method to get month start & End dates
	 *   */
	public static Map<String, Date> getMonthStartAndEndDate(Date date) {
		final Map<String, Date> map = new HashMap<String, Date>();
		if(date != null){
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.getActualMinimum(Calendar.MONTH);

		final Calendar end = Calendar.getInstance();
		end.setTime(calendar.getTime());
		end.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		map.put("START", calendar.getTime());
		map.put("END", end.getTime());
		}
		return map;
	}
	
	public static Integer getDaysBetweenDates(Date startDate, Date endDate) {
		if (startDate != null && endDate != null) {
			return Days.daysBetween(new DateTime(startDate), new DateTime(endDate)).getDays();
		}
		return null;
	}
	
	public static String getDateInNameMonthFormat(Date date){
		String dateString = null;
		if(date != null){
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dateString =formatter.format(date);
		}
		return dateString;
	}
	
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}
	
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}
	
	public static String getTaskStartDateFormat(Date date){
		String dateString = null;
		if(date != null){
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dateString =formatter.format(date)+" 00:00";
		}
		return dateString;
	}
	
	public static String getTaskEndDateFormat(Date date){
		Calendar cal = getDateWithoutTime(date);
		cal.add(Calendar.DATE, 1);
		String dateString = null;
		if(date != null){
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dateString =formatter.format(date);
		}
		return dateString;
	}
	
	public static String getTimeFromADate(Date date){
		String dateString = null;
		if(date != null){
			SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
			dateString =formatter.format(date);
		}
		return dateString;
	}

}

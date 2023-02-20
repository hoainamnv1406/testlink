package helpers;

import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {
    private static final String SIMPLE_FORMAT = "yyyy-MM-dd";
    private static final String SCHEDULE_PARADOX_TIME_FORMAT = "EEEE, MMM dd";
    private static final String SCHEDULE_PARADOX_LONG_MONTH_TIME_FORMAT = "EEEE, MMMM d";
    private static final String EVENT_DATE_FULL_TIME_FORMAT = "EEEE, MMMM d, YYYY";
    public static final String EVENT_DATE_FULL_FORMAT = "MMMM d, YYYY";
    public static final String EVENT_START_DATE_RECURRING = "MMM d, yyyy";
    private static final String HOUR_MINUTE_FORMAT = "hh:mm a";
    private static final String SIMPLE_MONTH_FORMAT = "MMM";
    public static final String EVENT_START_DATE_LANDING_PAGE = "EEEE, MMMM d, yyyy";

    public static String getCurrentTime() {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        return now.toString().replace(" ", "_");
    }

    public static String getDateTimeWithSimpleFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_FORMAT);
        try {
            return simpleDateFormat.format(new Date());
        } catch (Exception ex) {
            return "";
        }
    }

    public static int getCurrentDateOfMonth() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth();
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //add number would increase the days
        return cal.getTime();
    }

    public static String getNextDay(int moreDay) {
        Date nextDay = addDays(new Date(), moreDay);
        LocalDate localDate = nextDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return String.valueOf(localDate.getDayOfMonth());
    }

    public static String getNextDateFormatTime(int day) {
        Format formatter = new SimpleDateFormat(SCHEDULE_PARADOX_TIME_FORMAT);
        return formatter.format(addDays(new Date(), day));
    }

    public static String getNextDateHasLongMonthFormatTime(int day) {
        Format formatter = new SimpleDateFormat(SCHEDULE_PARADOX_LONG_MONTH_TIME_FORMAT);
        return formatter.format(addDays(new Date(), day));
    }

    public static String getTimeWithZeroDisplayFirstForSmallerTen(String time) {
        if (Integer.parseInt(time) < 10) {
            time = String.format("0%s", time);
        }
        return time;
    }

    public static String getDateTimeWithSimpleFormat(int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_FORMAT);
        try {
            Date date = addDays(new Date(), day);
            return simpleDateFormat.format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    public static int getCurrentDateOfMonth(int dayAdd) {
        Date date = addDays(new Date(), dayAdd);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth();
    }

    public static String getNextHourScheduleTime(int hour) {
        return getNextHourScheduleTimeWithFormatTime(hour, "hh:mm");
    }

    public static String convertTimeSchedule(int hour, int minutes) {
        if (minutes >= 0 && minutes < 30) {
            minutes = 30;
        } else {
            minutes = 0;
            hour += 1;
        }
        String time;
        if (minutes == 0) {
            time = String.format("%d:%d0", hour, minutes);
        } else {
            time = String.format("%d:%d", hour, minutes);
        }
        return time;
    }

    public static int calculateMinutesDuration(String startTime, String endTime) {
        if (startTime != null && endTime != null && !startTime.isEmpty() && !endTime.isEmpty()) {
            String[] startTimeArray = startTime.split(":");
            String[] endTimeArray = endTime.split(":");
            int hourStart = Integer.parseInt(startTimeArray[0]);
            int minutesStart = Integer.parseInt(startTimeArray[1]);
            int hourEnd = Integer.parseInt(endTimeArray[0]);
            int minuteEnd = Integer.parseInt(endTimeArray[1]);
            int sum = ((hourEnd - hourStart) * 60) + (minuteEnd - minutesStart);
            return sum;
        }
        return 0;
    }

    public static String getNextHourWithPeriodOption(int hour) {
        // 12 hour format with AM/PM //
        String time = getNextHourScheduleTimeWithFormatTime(hour, HOUR_MINUTE_FORMAT);
        String[] splitTime = time.split(":");
        time = String.valueOf(getTimeFormat12Hour(Integer.parseInt(splitTime[0]))).concat(":").concat(splitTime[1]);
        return time;
    }

    public static String getNextHourScheduleTimeWithFormatTime(int hour, String format) {
        try {
            LocalTime now = LocalTime.now();
            now = now.plusHours(hour);
            String timeWithFormat = now.format(DateTimeFormatter.ofPattern(format));
            if (format.contains("a")) {
                String[] arrayTime = timeWithFormat.split(" ");
                String time = arrayTime[0];
                String period = arrayTime[1];
                String[] arrayContainsNoPeriod = time.split(":");
                int minutes = Integer.parseInt(arrayContainsNoPeriod[1]);
                if (minutes >= 12) {
                    period = "PM";
                }
                int hourAdded = Integer.parseInt(arrayContainsNoPeriod[0]);
                return convertTimeSchedule(hourAdded, minutes).concat(" ").concat(period);
            } else {
                String[] arrayTime = timeWithFormat.split(":");
                int minutes = Integer.parseInt(arrayTime[1]);
                int hourAdded = Integer.parseInt(arrayTime[0]);
                return convertTimeSchedule(hourAdded, minutes);
            }
        } catch (Exception exception) {
            return "";
        }
    }

    public static List<String> getNextDayList(int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SCHEDULE_PARADOX_LONG_MONTH_TIME_FORMAT);
        List<String> listOfNextDay = new ArrayList<>();
        try {
            String dateFormat;
            for (int i = 0; i <= day; i++) {
                Date date = addDays(new Date(), i);
                dateFormat = simpleDateFormat.format(date);
                listOfNextDay.add(dateFormat);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfNextDay;
    }

    public static boolean isValidDate(String textDate) {
        Pattern DATE_PATTERN = Pattern.compile(
                "(?:Sun|Mon|Tue|Wed|Thu|Fri|Sat), " +
                        "(?:Jan|Feb|Mar|Apr|May|June?|July?|Aug|Sept?|Oct|Nov|Dec) " +
                        "\\d{2}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = DATE_PATTERN.matcher(textDate);
        return matcher.matches();
    }

    public static boolean isValidTime(String time) {
        Pattern TIME_PATTERN = Pattern.compile("([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])\\s*([AaPp][Mm]) - " +
                "([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])\\s*([AaPp][Mm]) (.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = TIME_PATTERN.matcher(time);
        return matcher.matches();
    }

    public static boolean checkSortDateCorrectly(List<String> dateStringsList, boolean isDescending) {
        if (dateStringsList != null && dateStringsList.size() >= 2) {
            try {
                List<org.joda.time.LocalDate> listDateObject = addYearForTextDate(dateStringsList);
                if (isDescending) {
                    Collections.sort(listDateObject, Collections.reverseOrder());
                } else {
                    Collections.sort(listDateObject);
                }
                List<String> listOfStringSorted = new ArrayList<>();
                org.joda.time.format.DateTimeFormatter dateFormatScheduleShortString = DateTimeFormat.forPattern("EEE, MMM dd");
                for (org.joda.time.LocalDate date : listDateObject) {
                    String dateText = date.toString(dateFormatScheduleShortString);
                    listOfStringSorted.add(dateText);
                }
                return dateStringsList.equals(listOfStringSorted);
            } catch (Exception exception) {
                return false;
            }
        } else {
            boolean result = false;
            // we don't need to sort if data list is only one item in list //
            if (dateStringsList != null && dateStringsList.size() == 1) {
                result = true;
            }
            return result;
        }
    }

    private static List<org.joda.time.LocalDate> addYearForTextDate(List<String> dateStringsList) {
        List<org.joda.time.LocalDate> listDateObject = new ArrayList<>();
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("EEE, MMM d yyyy");
        int year = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonth().getValue();
        List<String> dataAddedYear = new ArrayList<>();
        for (String date : dateStringsList) {
            if ((currentMonth == 1 || currentMonth == 2) && (date.contains("Nov") || date.contains("Dec"))) {
                date = String.format("%s %d", date, year - 1);
            } else {
                date = String.format("%s %d", date, year);
            }
            dataAddedYear.add(date);
        }
        dataAddedYear.stream().map((string) -> formatter.parseLocalDate(string)).forEach((localDate) -> {
            listDateObject.add(localDate);
        });

        return listDateObject;
    }

    public static String getNextZeroMinimumScheduleTime() {
        return getNextHourWithPeriodOption(0);
    }

    public static String addZeroToTime(int time) {
        String result = "";
        if (time < 10) {
            result = "0" + time;
        } else {
            if (time > 12) {
                result = addZeroToTime(time - 12);
            } else {
                result = String.valueOf(time);
            }
        }
        return result;
    }

    public static int getTimeFormat12Hour(int time) {
        if (time > 12) {
            return (time - 12);
        } else {
            return time;
        }
    }

    public static String getValidDate(String date) {
        int dateNumber = Integer.parseInt(date);
        if (dateNumber > 31) {
            dateNumber = dateNumber - 30;
        }
        return String.valueOf(dateNumber);
    }

    public static String getCurrentDateWithFullTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String getCurrentDayWithEventFormatTime() {
        Format formatter = new SimpleDateFormat(EVENT_DATE_FULL_TIME_FORMAT);
        return formatter.format(addDays(new Date(), 0));
    }

    public static String getNextHourWithPeriodWithAddZeroTime(int hour) {
        // 12 hour format with AM/PM //
        String time = getNextHourScheduleTimeWithFormatTime(hour, HOUR_MINUTE_FORMAT);
        try {
            String[] splitTime = time.split(":");
            time = addZeroToTime(Integer.parseInt(splitTime[0])).concat(":").concat(splitTime[1]);
            if (time.equals("12:00 AM")) {
                time = time.replace("AM", "PM");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return time;
    }

    public static String convertTimeTo24H(String time) {
        try {
            String[] splitTime = time.split(":");
            int hour = Integer.parseInt(splitTime[0]);
            String minusWithOption = splitTime[1];
            if (minusWithOption.contains("PM") && hour < 12) {
                hour = hour + 12;
            }
            String minus = minusWithOption.split(" ")[0];
            time = addZeroHour(hour).concat(":").concat(minus);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return time;
    }

    public static String convertShortMonthWithDateTime(String dateTime) {
        try {
            String[] splitDateTime = dateTime.split(" ");
            String month = splitDateTime[0].substring(0, 3);
            dateTime = (String.format("%s %s %s", month, splitDateTime[1], splitDateTime[2]));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return dateTime;
    }

    public static String plusHourToTime(String time, int plus) throws ParseException {
        String timeAfterPlus = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat(HOUR_MINUTE_FORMAT);
            Date d = df.parse(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.HOUR, plus);
            timeAfterPlus = df.format(cal.getTime());
        } catch (Exception e) {
            e.getMessage();
        }
        return convertTimeTo12H(timeAfterPlus);
    }

    public static String convertTimeTo12H(String time) {
        DateFormat df = new SimpleDateFormat(HOUR_MINUTE_FORMAT);
        DateFormat outPutFormat = new SimpleDateFormat(HOUR_MINUTE_FORMAT);
        Date date;
        String time12H = null;
        try {
            date = df.parse(time);
            time12H = outPutFormat.format(date);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return time12H;
    }

    public static String plusHourByText(String time, int plusHour) {
        try {
            String[] splitTime = time.split(":");
            int hour = Integer.parseInt(splitTime[0]);
            hour += plusHour;
            time = String.format("%s:%s", addZeroHour(hour), splitTime[1]);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return time;
    }

    private static String addZeroHour(int hour) {
        String result = "";
        if (hour < 10) {
            result = "0" + hour;
        } else {
            result = String.valueOf(hour);
        }
        return result;
    }


    public static String getDateWithZeroLeadingForMonthAndDate(int day) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(addDays(new Date(), day));
    }

    public static String getDateWithMonthDateYearWithDayOnWeekFormat(int day) {
        Format formatter = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        return formatter.format(addDays(new Date(), day));
    }


    public static String getDateWithMonthDateYearWithOutDayOnWeekFormat(int day) {
        Format formatter = new SimpleDateFormat("MMMM dd, yyyy");
        return formatter.format(addDays(new Date(), day));
    }

    public static String getDateWithFormatName(int day, String format) {
        Format formatter = new SimpleDateFormat(format);
        return formatter.format(addDays(new Date(), day));
    }

    public static String getDateWithFormatTextMonth(int day) {
        Format formatter = new SimpleDateFormat(EVENT_DATE_FULL_FORMAT);
        return formatter.format(addDays(new Date(), day));
    }

    public static String getCurrentMonthSimpleFormat() {
        Calendar cal = Calendar.getInstance();
        return (new SimpleDateFormat(SIMPLE_MONTH_FORMAT).format(cal.getTime()));
    }

    public static String getCurrentDayOfWeek(){
        return new SimpleDateFormat("EEE").format(new Date());
    }

    public static String getCurrentDay(){
        return new SimpleDateFormat("dd").format(new Date());
    }

    public static String getCurrentMonth(){
        return new SimpleDateFormat("MMM").format(new Date());
    }
}
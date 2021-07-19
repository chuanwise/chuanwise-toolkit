package cn.chuanwise.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtility extends StaticUtility {
    protected static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    protected static final long SECOND_MILLIS = 1000;
    protected static final long MINUTE_MILLIS = SECOND_MILLIS * 60;
    protected static final long HOUR_MILLIS = MINUTE_MILLIS * 60;
    protected static final long DAY_MILLIS = HOUR_MILLIS * 24;
    protected static final long MOUTH_MILLIS = DAY_MILLIS * 30;

    public static String toTimeLength(long time) {
        long mouth = time / MOUTH_MILLIS;
        time -= mouth * MOUTH_MILLIS;
        long days = time / DAY_MILLIS;
        time -= days * DAY_MILLIS;
        long hours = time / HOUR_MILLIS;
        time -= hours * HOUR_MILLIS;
        long minutes = time / MINUTE_MILLIS;
        time -= minutes * MINUTE_MILLIS;
        long seconds = time / SECOND_MILLIS;
        time -= seconds * SECOND_MILLIS;

        StringBuilder result = new StringBuilder();
        if (mouth > 0) {
            result.append(days + "月");
        }
        if (days > 0) {
            result.append(days + "天");
        }
        if (hours > 0) {
            result.append(hours + "小时");
        }
        if (minutes > 0) {
            result.append(minutes + "分钟");
        }
        if (result.length() == 0 && seconds > 0) {
            result.append(seconds + "秒");
        }
        if (result.length() == 0 && time > 0) {
            result.append(time + "毫秒");
        }
        return result.toString();
    }

    public static long parseTimeLength(final String timeString) {
        if (!timeString.matches("(\\d+[Dd天Hh时Mm分Ss秒])+")) {
            return -1;
        }
        long totalTime = 0;
        long currentNumber = 0;
        for (int index = 0; index < timeString.length(); index ++) {
            if (Character.isDigit(timeString.charAt(index))) {
                currentNumber *= 10;
                currentNumber += timeString.charAt(index) - '0';
                continue;
            }
            if ("Dd天".contains("" + timeString.charAt(index))) {
                totalTime += DAY_MILLIS * currentNumber;
                currentNumber = 0;
                continue;
            }
            if ("Hh时".contains("" + timeString.charAt(index))) {
                totalTime += HOUR_MILLIS * currentNumber;
                currentNumber = 0;
                continue;
            }
            if ("Mm分".contains("" + timeString.charAt(index))) {
                totalTime += MINUTE_MILLIS * currentNumber;
                currentNumber = 0;
                continue;
            }
            if ("Ss秒".contains("" + timeString.charAt(index))) {
                totalTime += SECOND_MILLIS * currentNumber;
                currentNumber = 0;
                continue;
            }
            if (Character.isSpaceChar(timeString.charAt(index))) {
                continue;
            }
            return -1;
        }
        return totalTime;
    }

    public static String after(long millis) {
        return after(System.currentTimeMillis(), millis);
    }

    public static String after(long from, long millis) {
        return toTimeLength(millis) + "后（" + DEFAULT_FORMAT.format(from + millis) + "）";
    }

    public static String format(long time, String format) {
        return new SimpleDateFormat(format).format(time);
    }

    public static String formatNow(String format) {
        return format(System.currentTimeMillis(), format);
    }

    public static String format(long time) {
        return DEFAULT_FORMAT.format(time);
    }

    public static String formatNow() {
        return DEFAULT_FORMAT.format(System.currentTimeMillis());
    }

    public static long parse(String timeString, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(timeString).getTime();
    }

    public static long parse(String timeString) throws ParseException {
        return DEFAULT_FORMAT.parse(timeString).getTime();
    }
}
package cn.chuanwise.common.util;

import java.sql.Timestamp;
import java.time.*;
import java.util.Date;
import java.util.Objects;

/**
 * 日期相关工具
 *
 * @author Chuanwise
 */
public class Dates
    extends StaticUtilities {
    
    /**
     * 上海时区
     */
    private static ZoneId SHANG_HAI_ZONE_ID = SHANG_HAI_ZONE_ID = ZoneId.of("Asia/Shanghai");
    
    /**
     * 北京本地日期时间转化为北京日期时间
     *
     * @param dateTime 北京本地日期时间
     * @return 北京日期时间
     */
    public static ZonedDateTime toBeijingZonedDateTime(LocalDateTime dateTime) {
        Objects.requireNonNull(dateTime, "local date time is null!");
    
        return ZonedDateTime.of(dateTime, SHANG_HAI_ZONE_ID);
    }
    
    /**
     * 带时区的日期时间转化为北京本地日期时间
     *
     * @param dateTime 带时区的日期时间
     * @return 北京本地日期时间
     */
    public static LocalDateTime toBeijingLocalDateTime(ZonedDateTime dateTime) {
        Objects.requireNonNull(dateTime, "zoned date time is null!");
    
        if (dateTime.getZone() == SHANG_HAI_ZONE_ID) {
            return dateTime.toLocalDateTime();
        } else {
            return LocalDateTime.ofInstant(dateTime.toInstant(), SHANG_HAI_ZONE_ID);
        }
    }
    
    /**
     * 北京本地日期时间转化为日期
     *
     * @param dateTime 北京本地日期时间
     * @return 日期
     */
    public static Date toBeijingDate(LocalDateTime dateTime) {
        Objects.requireNonNull(dateTime, "local date time is null!");
    
        return new Date(Timestamp.from(ZonedDateTime.of(dateTime, SHANG_HAI_ZONE_ID).toInstant()).getTime());
    }
    
    /**
     * 日期转化为北京本地日期时间
     *
     * @param date 日期
     * @return 北京本地日期时间
     */
    public static LocalDateTime toBeijingLocalDateTime(Date date) {
        Objects.requireNonNull(date, "date time is null!");
    
        return LocalDateTime.ofInstant(date.toInstant(), SHANG_HAI_ZONE_ID);
    }
    
    /**
     * 本地日期时间转化为北京时刻
     *
     * @param dateTime 本地日期时间
     * @return 北京时刻
     */
    public static Instant toInstant(LocalDateTime dateTime) {
        Objects.requireNonNull(dateTime, "local date time is null!");
        
        return dateTime.atZone(SHANG_HAI_ZONE_ID).toInstant();
    }
    
    /**
     * 北京时刻转化为北京本地日期时间
     *
     * @param instant 北京时刻
     * @return 北京本地日期时间
     */
    public static LocalDateTime toLocalDateTime(Instant instant) {
        Objects.requireNonNull(instant, "instant is null!");
    
        return instant.atZone(SHANG_HAI_ZONE_ID).toLocalDateTime();
    }
}
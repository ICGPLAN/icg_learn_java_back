package jp.co.icg.base.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static String getYMD() {
        LocalDate now = LocalDate.now(ZoneId.of("Asia/Tokyo"));
        return now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static String getY() {
        LocalDate now = LocalDate.now(ZoneId.of("Asia/Tokyo"));
        return now.format(DateTimeFormatter.ofPattern("yyyy"));
    }

    public static String getMD() {
        LocalDate now = LocalDate.now(ZoneId.of("Asia/Tokyo"));
        return now.format(DateTimeFormatter.ofPattern("MMdd"));
    }

    public static String getYMDHms() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
    }

    public static LocalDate toDateYMD(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(str, formatter);
    }

    public static String toYMD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
        return format.format(date);
    }
}

package com.pikapika.app.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
	
	public static String getDayByFormat(LocalDateTime localDateTime, String timeFormat){
        return localDateTime.format(DateTimeFormatter.ofPattern(timeFormat));
    }
}

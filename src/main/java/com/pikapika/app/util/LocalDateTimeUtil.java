package com.pikapika.app.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * 时间工具
 * @author jerryCor
 *
 */
public class LocalDateTimeUtil {
	/**
	 * 时间转换
	 * @param localDateTime 具体时间
	 * @param timeFormat 转换格式
	 * @return 时间(string格式)
	 */
	public static String getDayByFormat(LocalDateTime localDateTime, String timeFormat){
        return localDateTime.format(DateTimeFormatter.ofPattern(timeFormat));
    }
}

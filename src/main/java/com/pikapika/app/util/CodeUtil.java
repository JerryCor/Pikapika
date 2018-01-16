package com.pikapika.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * code工具
 * @author jerryCor
 *
 */
public class CodeUtil {
	/**
	 * 判断字符串是否是数字
	 * @param str
	 * @return true: 是 false:否
	 */
	public static boolean isNub(String str){
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true;
	}
}

package com.pikapika.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeUtil {
	public static boolean isNub(String str){
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true;
	}
}

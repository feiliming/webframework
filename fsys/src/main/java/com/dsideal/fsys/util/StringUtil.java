package com.dsideal.fsys.util;

/**
 * 
 * @author feilm220
 *
 */
public class StringUtil {

	/**
	 * 去掉左右空格,并判断是否为null或者""
	 */
	public static boolean isNullOrEmpty(String str){
		return str == null || str.trim().length() == 0;
	}
	
}

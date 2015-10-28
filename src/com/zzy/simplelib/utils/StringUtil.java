package com.zzy.simplelib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 字符串处理 */
public class StringUtil {

	/***
	 * 是否是空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null) {
			str = str.replace(" ", "");
			return str.length() == 0;
		}
		return true;
	}

	/**
	 * 邮箱地址是否合法
	 * 
	 * @param email
	 * @return
	 */
	public static boolean emailIsOk(String email) {
		if (isEmpty(email)) {
			return false;
		}
		String check = "^([a-z0-9A-Z]+[-_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z-_]+(-_[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}

	/**
	 * 匹配字符串
	 * 
	 * @param str
	 * @param str1
	 * @return
	 */
	public static boolean isEqual(String str, String str1) {
		return str.equals(str1);
	}

	/***
	 * 是否是手机号
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobileNumber(String str) {
		if (str != null) {
			str = str.replace(" ", "");
			return str.length() == 11;
		}
		return false;
	}

}

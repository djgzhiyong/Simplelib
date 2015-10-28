package com.zzy.simplelib.utils;

import java.text.DecimalFormat;

/** 数字格式 */
public class NumberUtil {

	/***
	 * 4舍5入保留2位小数
	 */
	public static String round2PointNumber(double d) {
		DecimalFormat mDecimalFormat = new DecimalFormat("0.00");
		return mDecimalFormat.format(d);
	}

}

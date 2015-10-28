package com.zzy.simplelib.utils;

import android.content.Context;
import android.content.SharedPreferences;

/** SharedPreferences 存储 */
public class BaseSaveUtil {

	private static SharedPreferences mPreferences;

	private static final String CONFIG_NAME = "config";

	private static void loadSharedPreferences(Context context) {
		mPreferences = context.getSharedPreferences(CONFIG_NAME,
				Context.MODE_PRIVATE);
	}

	/***
	 * 保存布尔类型值
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            存储键
	 * @param value
	 *            存储值
	 */
	public static void saveBooleanConfig(Context context, String key,
			boolean value) {
		loadSharedPreferences(context);
		mPreferences.edit().putBoolean(key, value).commit();
	}

	/***
	 * 存储字符串类型值
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            存储键
	 * @param value
	 *            存储值
	 */
	public static void saveStringConfig(Context context, String key,
			String value) {
		loadSharedPreferences(context);
		mPreferences.edit().putString(key, value).commit();
	}

	/***
	 * 存储整型值
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            存储键
	 * @param value
	 *            存储值
	 */
	public static void saveIntegerConfig(Context context, String key, int value) {
		loadSharedPreferences(context);
		mPreferences.edit().putInt(key, value).commit();
	}

	/**
	 * 获取布尔存储值
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            存储键
	 * @return 存储值
	 */
	public static boolean getBooleanConfig(Context context, String key) {
		loadSharedPreferences(context);
		return mPreferences.getBoolean(key, false);
	}

	/**
	 * 获取字符串存储值
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            存储键
	 * @return 存储值
	 */
	public static String getStringConfig(Context context, String key) {
		loadSharedPreferences(context);
		return mPreferences.getString(key, null);
	}

	/**
	 * 获取整型存储值
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            存储键
	 * @return 存储值
	 */
	public static int getIntegerConfig(Context context, String key) {
		loadSharedPreferences(context);
		return mPreferences.getInt(key, 0);
	}
}

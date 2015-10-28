package com.zzy.simplelib.utils;

import android.util.Log;

public class LogUtil {

	public static String TAG = "simplelib";
	public static final boolean isDebug = true;

	public static void red(Object msg) {
		if (isDebug)
			Log.e(TAG, msg + "");
	}

	public static void green(Object msg) {
		if (isDebug)
			Log.i(TAG, msg + "");
	}

}

package com.zzy.simplelib.utils;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/** 获取当前应用程序相关信息 */
public class AppUtil {

	/**
	 * 获取应用程序版本名称
	 * 
	 * @return 版本名称
	 */
	public static String getApplicationVersionName(Activity activity) {
		PackageManager packageManager = activity.getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(activity.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "1";
	}

	/**
	 * 获取应用程序版本号
	 * 
	 * @return 版本号
	 */
	public static int getApplicationVersionCode(Activity activity) {
		PackageManager packageManager = activity.getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(activity.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * 跳转界面
	 * 
	 * @param activity
	 *            当前界面
	 * @param targetActivity
	 *            目标界面
	 */
	public static <T> void jumpActivity(Activity activity, Class<T> targetActivity) {
		Intent intent = new Intent(activity, targetActivity);
		activity.startActivity(intent);
	}

	/**
	 * 跳转界面并附加数据
	 * 
	 * @param activity
	 *            当前界面
	 * @param targetActivity
	 *            目标界面
	 * @param intent
	 *            附加数据
	 */
	public static <T> void jumpActivity(Activity activity, Class<T> targetActivity, Intent intent) {
		intent.setClass(activity, targetActivity);
		activity.startActivity(intent);
	}

	/***
	 * 跳转界面并返回响应
	 * 
	 * @param activity
	 *            当前界面
	 * @param targetActivity
	 *            目标界面
	 * @param requestCode
	 *            响应值
	 */
	public static <T> void jumpActivity(Activity activity, Class<T> targetActivity, int requestCode) {
		Intent intent = new Intent(activity, targetActivity);
		activity.startActivityForResult(intent, requestCode);
	}

	/***
	 * 跳转界面附加数据并返回响应
	 * 
	 * @param activity
	 *            当前界面
	 * @param targetActivity
	 *            目标界面
	 * @param requestCode
	 *            响应值
	 */
	public static <T> void jumpActivity(Activity activity, Class<T> targetActivity, Intent intent, int requestCode) {
		intent.setClass(activity, targetActivity);
		activity.startActivityForResult(intent, requestCode);
	}

	/***
	 * 跳转界面附加数据并返回响应
	 * 
	 * @param activity
	 *            当前界面
	 * @param targetActivity
	 *            目标界面
	 * @param requestCode
	 *            响应值
	 */
	public static <T> void jumpActivityNewTask(Activity activity, Class<T> targetActivity) {
		Intent intent = new Intent(activity, targetActivity);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(intent);
	}

	/**
	 * 应用程序是否在前台运行
	 * 
	 * @param context
	 *            上下文
	 * @return 是否前台运行
	 */
	public static boolean applicationIsRunning(Context context) {
		String packageName = context.getPackageName();
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			if (packageName.equals(tasksInfo.get(0).topActivity.getPackageName())) {
				return true;
			}
		}
		return false;
	}

}

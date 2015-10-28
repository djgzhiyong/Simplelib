package com.zzy.simplelib.utils;

import java.io.File;

import android.Manifest.permission;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

/** 运行平台 */
public class PlatformUtil {

	/**
	 * 直接打电话
	 * 
	 * @param context
	 *            上下文
	 * @param phone
	 *            电话号码
	 * 
	 * @exception permission
	 */
	public static void callPhone(Context context, String phone) {
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel://" + phone));
		context.startActivity(intent);
	}

	/***
	 * 获取网络状态
	 * 
	 * @param context
	 *            上下文
	 * @return 网络断开链接： NETWORK_DISCONNECTION；WIFI网络链接：NETWORK_WIFI_CONNECTION
	 *         ；手机移动网络链接：NETWORK_MOBILE_CONNECTION
	 * 
	 * @exception permission
	 */
	public static int getNetWorkStatus(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager != null) {
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isConnected()) {
				if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
					return NETWORK_WIFI_CONNECTION;
				}
				return NETWORK_MOBILE_CONNECTION;
			}
		}
		return NETWORK_DISCONNECTION;
	}

	/***
	 * 系统下载
	 * 
	 * @param context
	 *            上下文
	 * @param title
	 *            标题
	 * @param url
	 *            地址
	 * @param savePath
	 *            保存路径
	 * @return 下载唯一Key
	 */
	public static long systemDownload(Context context, String title, String url, String savePath) {
		DownloadManager mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
		Request mRequest = new Request(Uri.parse(url));
		mRequest.setDestinationUri(Uri.fromFile(new File(savePath)));
		mRequest.setTitle(title);
		return mDownloadManager.enqueue(mRequest);
	}

	/***
	 * 从URL中获取存储路径
	 * 
	 * @param context
	 *            上下文
	 * @param uri
	 *            存储URI
	 * @return 存储路径
	 */
	public static String getPathFromUri(Activity context, Uri uri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = context.managedQuery(uri, proj, null, null, null);
		int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		String path = cursor.getString(index);
		return path;
	}

	/***
	 * 安装apk文件
	 * 
	 * @param context
	 *            上下文
	 * @param path
	 *            apk文件地址
	 */
	public static void installApk(Context context, String path) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/***
	 * 获取设备ID
	 * 
	 * @param context
	 *            上下文
	 * @return 设备ID
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return mTelephonyManager.getDeviceId();
	}

	/***
	 * 获取手机号码
	 * 
	 * @param context
	 *            上下文
	 * @return 手机号码
	 */
	public static String getPhoneNum(Context context) {
		TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return mTelephonyManager.getSimSerialNumber();
	}

	/**
	 * 获取设备水平像素值
	 * 
	 * @param activity
	 * @return
	 */
	public static int getScreenWidth(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}

	/**
	 * 获取设备垂直像素值
	 * 
	 * @param activity
	 * @return
	 */
	public static int getScreenHeight(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}

	/** 打开GPS */
	public static void openGPS(Activity activity) {
		Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		activity.startActivity(intent);
	}

	/***
	 * 打开图库
	 */
	public static void openGallery(Activity activity, int resultCode) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_PICK);
		intent.setType("image/*");
		activity.startActivityForResult(intent, resultCode);
	}

	/***
	 * 打开相机
	 */
	public static void openCamera(Activity context, String path, int resultCode) {
		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
		context.startActivityForResult(intent, resultCode);
	}

	/** 网络断开链接 */
	public static final int NETWORK_DISCONNECTION = 0x1;

	/** WIFI网络链接 */
	public static final int NETWORK_WIFI_CONNECTION = 0x2;

	/** 手机移动网络链接 */
	public static final int NETWORK_MOBILE_CONNECTION = 0x3;

}

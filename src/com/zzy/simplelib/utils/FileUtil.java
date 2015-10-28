package com.zzy.simplelib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.os.Environment;

/** 文件读写 */
public class FileUtil {

	/**
	 * 获取应用根存储路径
	 * 
	 * @param context
	 *            上下文
	 * @return 根存储路径
	 */
	public static String getRootPath(Context context) {
		String pathString = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + context.getPackageName()
				+ "/";
		File f = new File(pathString);
		if (!f.exists()) {
			f.mkdirs();
		}
		return pathString;
	}

	/***
	 * 写出字符串
	 * 
	 * @param context
	 *            上下文
	 * @param path
	 *            目标文件
	 * @param content
	 *            内容
	 */
	public static void writeContent(String path, String content) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(path, false);
			fw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 文件是否存在
	 * 
	 * @param filepath
	 *            文件完整路径
	 * @return
	 */
	public static boolean fileIsExists(String filepath) {
		return new File(filepath).exists();
	}

	/**
	 * 拷贝文件
	 * 
	 * @param filePath
	 *            当前文件路径
	 * @param targetPath
	 *            拷贝到的目标路径
	 */
	public static void copyFile(String filePath, String targetPath) {
		File file = new File(filePath);
		if (file.exists()) {
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(file);
				fos = new FileOutputStream(new File(targetPath));
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				fos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fos != null) {
						fos.close();
					}
					if (fis != null) {
						fis.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void downloadFileFromRemoteUrl(String urlStr, String savePath) {
		InputStream input = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			input = conn.getInputStream();
			File file = new File(savePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			fos = new FileOutputStream(new File(savePath));
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = input.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** 拷贝文件夹 */
	public static void copyDir(File nowFile, String newPath) {
		if (nowFile.isDirectory()) {
			File[] fileArray = nowFile.listFiles();
			if (fileArray != null && fileArray.length > 0) {
				for (int i = 0; i < fileArray.length; i++) {
					copyDir(fileArray[i], newPath);
				}
			} else {
				File emptyDir = new File(newPath + "/" + nowFile.getAbsolutePath());
				emptyDir.mkdirs();
			}
		} else {
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(nowFile);
				File file = new File(newPath + "/" + getPathSuffix(nowFile));

				File dirFile = new File(file.getParentFile().getAbsolutePath() + "/");
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}

				fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				fos.flush();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (fos != null) {
						fos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String getPathSuffix(File nowDir) {
		String strPath = nowDir.getAbsolutePath();
		return strPath.substring(strPath.lastIndexOf(":") + 1, strPath.length());
	}
}

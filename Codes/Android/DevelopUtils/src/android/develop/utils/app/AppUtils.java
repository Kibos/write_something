package android.develop.utils.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.develop.utils.InnerContacts;
import android.develop.utils.log.LogUtils;
import android.graphics.drawable.Drawable;

/**
 * APP 工具类<br>
 * 内部已经封装了打印功能,只需要把DEBUG参数改为true即可<br>
 * 如果需要更换tag可以直接更改,默认为KEZHUANG
 * 
 * @author KEZHUANG
 */
public class AppUtils {
	/**
	 * Log的开关<br>
	 * true为开启<br>
	 * false为关闭<br>
	 */
	public static boolean DEBUG = InnerContacts.DEFAULT_DEBUG;

	/**
	 * Log 输出标签
	 */
	public static String TAG = InnerContacts.DEFAULT_TAG;

	/**
	 *  获得APP的label名字
	 * @param context
	 * @return
	 */
	public static String getAppName(Context context) {
		if (context == null) {
			LogUtils.DEBUG = !InnerContacts.DEFAULT_DEBUG;
			LogUtils.e("getAppName  context为空");
			LogUtils.DEBUG=InnerContacts.DEFAULT_DEBUG;
			return null;
		}
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			String appName = context.getResources().getString(labelRes);
			LogUtils.inner_i("APP名字：" + appName, DEBUG);
			return appName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取应用程序版本名称信息
	 */
	public static String getVersionName(Context context) {
		if (context == null) {
			LogUtils.inner_e("getVersionName  context为空", !InnerContacts.DEFAULT_DEBUG);
			return null;
		}
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			String versionCode = packageInfo.versionName;
			LogUtils.inner_i("APP版本：" + versionCode, DEBUG);
			return packageInfo.versionName;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取应用程序包名
	 */
	public static String getPackageName(Context context) {
		if (context == null) {
			LogUtils.inner_e("getPackageName  context为空", !InnerContacts.DEFAULT_DEBUG);
			return null;
		}
		String pkgName = context.getPackageName();
		LogUtils.inner_i("APP包名：" + pkgName, DEBUG);
		return pkgName;
	}
	
	@SuppressLint("NewApi")
	public static Drawable getIcon(Context context){
		if (context == null) {
			LogUtils.inner_e("getPackageName  context为空", !InnerContacts.DEFAULT_DEBUG);
			return null;
		}
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int icon = packageInfo.applicationInfo.icon;
			Drawable drawable = context.getResources().getDrawable(icon);
			LogUtils.inner_i("App 图标：" + drawable.toString(), DEBUG);
			return drawable;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}

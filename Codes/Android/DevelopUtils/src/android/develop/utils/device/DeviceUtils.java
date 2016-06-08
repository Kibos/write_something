package android.develop.utils.device;

import android.content.Context;
import android.develop.utils.InnerContacts;
import android.develop.utils.log.LogUtils;
import android.telephony.TelephonyManager;

/**
 * 获取手机信息工具类<br>
 * 内部已经封装了打印功能,只需要把DEBUG参数改为true即可<br>
 * 如果需要更换tag可以直接更改,默认为KEZHUANG
 * 
 * @author KEZHUANG
 *
 */
public class DeviceUtils {
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
	 * 获取应用程序的IMEI号
	 */
	public static String getIMEI(Context context) {
		if (context == null) {
			LogUtils.inner_e("getIMEI  context为空", !InnerContacts.DEFAULT_DEBUG);
		}
		TelephonyManager telecomManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telecomManager.getDeviceId();
		LogUtils.inner_i("IMEI标识：" + imei, DEBUG);
		return imei;
	}

	/**
	 * 获取设备的系统版本号
	 */
	public static int getDeviceSDK() {
		int sdk = android.os.Build.VERSION.SDK_INT;
		LogUtils.inner_i("设备版本：" + sdk, DEBUG);
		return sdk;
	}

	/**
	 * 获取设备的型号
	 */
	public static String getDeviceName() {
		String model = android.os.Build.MODEL;
		LogUtils.inner_i("设备型号：" + model, DEBUG);
		return model;
	}
}

package android.develop.utils.density;

import android.content.Context;
import android.develop.utils.InnerContacts;
import android.develop.utils.log.LogUtils;
import android.util.TypedValue;

/**
 * 单位转换 工具类<br>
 * 内部已经封装了打印功能,只需要把DEBUG参数改为true即可<br>
 * 如果需要更换tag可以直接更改,默认为KEZHUANG
 * 
 * @author KEZHUANG
 *
 */
public class DensityUtils {
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
	 * dp转px
	 * 
	 */
	public static int dp2px(Context context, float dpVal) {
		int result = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources()
						.getDisplayMetrics());
		LogUtils.inner_i("dp-->px：" + result, DEBUG);
		return result;
	}

	/**
	 * sp转px
	 */
	public static int sp2px(Context context, float spVal) {
		int result = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources()
						.getDisplayMetrics());
		LogUtils.inner_i("sp-->px：" + result, DEBUG);
		return result;
	}

	/**
	 * px转dp
	 * 
	 */
	public static int px2dp(Context context, float pxVal) {
		final float scale = context.getResources().getDisplayMetrics().density;
		int result = (int) (pxVal / scale);
		LogUtils.inner_i("px-->dp：" + result, DEBUG);
		return result;
	}

	/**
	 * px转sp
	 */
	public static float px2sp(Context context, float pxVal) {
		int result = (int) (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
		LogUtils.inner_i("px-->sp：" + result, DEBUG);
		return result;
	}

}

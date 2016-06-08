package android.develop.utils.view;

import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * 视图 工具类
 * 
 * @author KEZHUANG
 *
 */
public class ViewUtils {
	/**
	 * 修改普通View的高<br>
	 * Adapter---getView方法中慎用
	 */
	public static void changeH(View v, int H) {
		LayoutParams params = (LayoutParams) v.getLayoutParams();
		params.height = H;
		v.setLayoutParams(params);
	}

	/**
	 * 修改普通View的宽<br>
	 * Adapter---getView方法中慎用
	 */
	public static void changeW(View v, int W) {
		LayoutParams params = (LayoutParams) v.getLayoutParams();
		params.width = W;
		v.setLayoutParams(params);
	}

	/**
	 * 修改控件的宽高<br>
	 * Adapter---getView方法中慎用
	 * 
	 * @param v
	 *            控件
	 * @param W
	 *            宽度
	 * @param H
	 *            高度
	 */
	public static void changeWH(View v, int W, int H) {
		LayoutParams params = (LayoutParams) v.getLayoutParams();
		params.width = W;
		params.height = H;
		v.setLayoutParams(params);
	}
}

package com.example.netrequestdemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
/**
 * @author 王永飞
 * 平台  IT蓝豹 www.itlanbao.com
 * ImageLoader 加载网络图片例子
 * 本类主要是通过DisplayImageOptions 实现加载图片
 */
public class ImageLoader extends Activity {

	private ImageView mImageView;

	private ImageView image2;
	public static final String CACHE_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/zhbj_cache_52";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageloader_request);

		mImageView = (ImageView) findViewById(R.id.image);
		image2 = (ImageView) findViewById(R.id.image2);

		String imageUrl = "http://img.mukewang.com/552640c300018a9606000338-300-170.jpg";
		ImageSize mImageSize = new ImageSize(100, 100);

		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		/**
		 * loadImage()加载图片
		 */
		com.nostra13.universalimageloader.core.ImageLoader.getInstance()
				.loadImage(imageUrl, mImageSize, options,
						new SimpleImageLoadingListener() {

							@Override
							public void onLoadingStarted(String imageUri,
									View view) {

							}

							@Override
							public void onLoadingFailed(String imageUri,
									View view, FailReason failReason) {

							}

							@Override
							public void onLoadingComplete(String imageUri,
									View view, Bitmap loadedImage) {
								mImageView.setImageBitmap(loadedImage);
							}

							@Override
							public void onLoadingCancelled(String imageUri,
									View view) {

							}
						});

		/**
		 * displayImage()加载图片
		 */
		DisplayImageOptions options2 = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.checked).cacheInMemory(true)
				.cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

		com.nostra13.universalimageloader.core.ImageLoader.getInstance()
				.displayImage(imageUrl, image2, options2);
		com.nostra13.universalimageloader.core.ImageLoader.getInstance()
				.displayImage(imageUrl, image2, options2,
						new SimpleImageLoadingListener(),
						new ImageLoadingProgressListener() {

							@Override
							public void onProgressUpdate(String arg0,
									View arg1, int arg2, int arg3) {

							}
						});
	}

}

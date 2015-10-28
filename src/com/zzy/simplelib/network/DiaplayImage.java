package com.zzy.simplelib.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.graphics.Bitmap;

public class DiaplayImage {

	public static void loadImg(Context context) {
		RequestQueue queue = Volley.newRequestQueue(context);
		ImageLoader imageLoader = new ImageLoader(queue, new ImageCache() {
			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				
			}

			@Override
			public Bitmap getBitmap(String url) {
				return null;
			}
		});
	}

}

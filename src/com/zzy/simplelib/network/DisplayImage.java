package com.zzy.simplelib.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class DisplayImage {

	private RequestQueue queue;

	private static DisplayImage self;

	private DisplayImage(Context context) {
		queue = Volley.newRequestQueue(context);
	}

	public static DisplayImage getInstance(Context context) {
		if (self == null) {
			self = new DisplayImage(context);
		}
		return self;
	}

	public void loadImg(String url, final ImageView img, int defaultResId, int errorResId) {
		ImageLoader imageLoader = new ImageLoader(queue, new ImageCache() {
			@Override
			public void putBitmap(String url, Bitmap bitmap) {
			}

			@Override
			public Bitmap getBitmap(String url) {
				return null;
			}
		});
		ImageListener imageListener = ImageLoader.getImageListener(img, defaultResId, errorResId);
		imageLoader.get(url, imageListener);
	}

}

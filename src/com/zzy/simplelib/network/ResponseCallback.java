package com.zzy.simplelib.network;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public abstract class ResponseCallback<T> implements Listener<T>, ErrorListener {

	@Override
	public void onResponse(T t) {
		System.out.println("t=" + t);
		succeed(t);
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		System.out.println("error=" + error.getMessage());
		fail(error.getMessage());
	}

	public abstract void succeed(T response);

	public abstract void fail(String msg);

}

package com.zzy.simplelib.network;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;

public class HttpHelper {

	private RequestQueue requestQueue;

	private HttpHelper(Context context) {
		requestQueue = Volley.newRequestQueue(context);
	}

	private static HttpHelper self;

	public static HttpHelper getInstance(Context context) {
		if (self == null) {
			self = new HttpHelper(context);
		}
		return self;
	}

	public void sendGetRequest(String url, ResponseCallback<String> callback) {
		StringRequest stringRequest = new StringRequest(url, callback, callback);
		requestQueue.add(stringRequest);
	}

	public void sendPostRequest(String url, ResponseCallback<String> callback) {
		StringRequest stringRequest = new StringRequest(Method.POST, url, callback, callback);
		requestQueue.add(stringRequest);
	}

	public void sendJsonGetRequest(String url, ResponseCallback<JSONObject> callback) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, callback, callback);
		requestQueue.add(jsonObjectRequest);
	}

	public void sendJsonPostRequest(String url, String jsonContent, ResponseCallback<JSONObject> callback) {
		try {
			JSONObject jsonObject = new JSONObject(jsonContent);
			JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.POST, url, jsonObject, callback,
					callback);
			requestQueue.add(jsonObjectRequest);
		} catch (JSONException e) {
			e.printStackTrace();
			callback.fail(e.getMessage());
		}
	}
}

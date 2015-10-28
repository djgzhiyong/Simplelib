package com.zzy.simplelib.view;

import com.example.simplelib.R;
import com.zzy.simplelib.network.HttpHelper;
import com.zzy.simplelib.network.ResponseCallback;

import android.view.MenuItem;

public class MainActivity extends ActivePage {

	@Override
	public int findView() {
		return R.layout.main;
	}

	@Override
	public void build() {

	}

	@Override
	public int getMenuResId() {
		return R.menu.main;
	}

	@Override
	public void menuItemClick(MenuItem item) {
		System.out.println("menu click=" + item.getItemId());
		HttpHelper.getInstance(this).sendGetRequest("http://www.baidu.com", new ResponseCallback<String>() {
			@Override
			public void succeed(String t) {

			}

			@Override
			public void fail(String msg) {

			}
		});
	}

}

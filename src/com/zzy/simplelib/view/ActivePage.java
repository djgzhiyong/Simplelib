package com.zzy.simplelib.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public abstract class ActivePage extends Activity {

	private Toast mToast;
	private ProgressDialog mProgressDialog;

	public abstract int findView();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(findView());
		build();
	}

	public abstract void build();

	public int getMenuResId() {
		return 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (getMenuResId() > 0) {
			getMenuInflater().inflate(getMenuResId(), menu);
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		menuItemClick(item);
		return super.onOptionsItemSelected(item);
	}

	public void menuItemClick(MenuItem item) {

	}

	public void showToast(String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
		}
		mToast.setText(msg);
		mToast.show();
	}

	public void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
		}
		mProgressDialog.show();
	}

	public void dismissProgressDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
	}

}

package com.zzy.simplelib.root;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityManager {

	private static List<Activity> mActivities;
	private static ActivityManager self;

	private ActivityManager() {
		mActivities = new ArrayList<Activity>();
	}

	public static ActivityManager getInstance() {
		if (self == null) {
			self = new ActivityManager();
		}
		return self;
	}

	public void addActivity(Activity activity) {
		mActivities.add(activity);
	}

	public void finishAll() {
		for (Activity mActivity : mActivities) {
			mActivity.finish();
		}
	}

	public <T> void finishActivity(Class<T> activity) {
		if (activity == null) {
			return;
		}
		for (Activity mActivity : mActivities) {
			if (mActivity.getClass().getName().equals(activity.getName())) {
				mActivity.finish();
			}
		}
	}

	public <T> void finishToActivity(Class<T> activity) {

		System.out
				.println("return to Activity--" + activity.getCanonicalName());

		int allCount = mActivities.size() - 1;

		for (int i = allCount; i >= 0; i--) {

			Activity tempActivity = mActivities.get(i);

			if (tempActivity.getClass().getName()
					.equals(activity.getCanonicalName())) {
				System.out.println("return  by "
						+ tempActivity.getClass().getName());
				return;
			}
			tempActivity.finish();
			System.out.println("finishActivity--"
					+ tempActivity.getClass().getName());
		}
	}

	public void remove(Activity activity) {
		mActivities.remove(activity);
	}

}

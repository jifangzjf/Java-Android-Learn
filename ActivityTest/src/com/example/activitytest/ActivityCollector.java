package com.example.activitytest;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;

public class ActivityCollector {
	private static List<Activity> activityList = new ArrayList<>();

	public static void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public static void removeActivity(Activity activity) {
		activityList.remove(activity);
	}

	public static void finishAll() {
		for (Activity activity : activityList) {
			activity.finish();
		}
	}
}

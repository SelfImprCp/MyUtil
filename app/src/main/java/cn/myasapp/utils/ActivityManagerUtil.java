package cn.myasapp.utils;

import java.util.Stack;

import android.app.Activity;

/*  
 * activity管理类，防止activity跳转混乱
 */
public class ActivityManagerUtil {

	// 接收activity的Stack
	private static Stack<Activity> activityStack = null;
	private static ActivityManagerUtil activityManager = null;

	private ActivityManagerUtil() {
	}

	public static ActivityManagerUtil getInstance() {

		if (activityManager == null) {
			activityManager = new ActivityManagerUtil();

		}
		return activityManager;
	}

	// 将activity移出栈
	public void popActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
		}
	}

	// 结束指定activity
	public void endActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	// 获得当前的activity(即最上层)
	public Activity currentActivity() {
		Activity activity = null;
		if (!activityStack.empty()) {
			activity = activityStack.lastElement();
		}
		return activity;
	}

	// 将activity推入栈内
	public void pushActivty(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	// 弹出除cls外的所有activity
	public void popAllActivityExceptOne(Class<? extends Activity> cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);

		}
	}

	// 结束除cls之外的所有activity,执行结果都会清空Stack
	public void finishAllActivityExceptOne(Class<? extends Activity> cls) {
		while (!activityStack.empty()) {
			Activity activity = currentActivity();
			if (activity.getClass().equals(cls)) {
				popActivity(activity);
			} else {
				endActivity(activity);
			}

		}
	}

	// 结束所有activity
	public void finishAllActivity() {
		while (!activityStack.empty()) {
			Activity activity = currentActivity();
			endActivity(activity);

		}
	}
}

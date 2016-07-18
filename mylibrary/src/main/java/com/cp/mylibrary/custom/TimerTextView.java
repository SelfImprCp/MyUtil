package com.cp.mylibrary.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自定义计算倒计时
 * 
 * @author Administrator
 * 
 */
public class TimerTextView extends TextView implements Runnable {
	private int day, hour, minute, second;
	private boolean isRun = false;

	public TimerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public TimerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TimerTextView(Context context) {
		super(context);
	}

	public void setTimes(long time) {

		// this.second = (int) (time / 1000) % 60;
		// this.minute = (int) (time / (60 * 1000) % 60);
		// this.hour = (int) (time / (60 * 60 * 1000) % 24);
		// this.day = (int) (time / (24 * 60 * 60 * 1000));
		this.minute = (int) Math.floor(time / 60);
		this.second = (int) (time - minute * 60);
	}

	/**
	 * 
	 * @return
	 */
	public String showTime() {
		StringBuilder time = new StringBuilder();
		// time.append(day);
		// time.append("天");
		// time.append(hour);
		// time.append("小时");
//		time.append(minute);
//		time.append("：");
		time.append(second);
		time.append("");

		return time.toString();
	}

	/**
	 * 瀹炵幇鍊掕鏃�
	 */
	private void countdown() {
		if (second == 0) {
			if (minute == 0) {
				if (hour == 0) {
					if (day == 0) {
						isRun = false;
						return;
					} else {
						day--;
					}
					hour = 23;
				} else {
					hour--;
				}
				minute = 59;
			} else {
				minute--;
			}
			second = 60;
		}

		second--;
	}

	public boolean isRun() {
		return isRun;
	}

	/**
	 */
	public void start() {
		isRun = true;
		run();
	}

	/**
	 */
	public void stop() {
		isRun = false;
	}

	/**
	 */
	@Override
	public void run() {
		if (isRun) {
			// Log.d(TAG, "Run");
			countdown();
			this.setText(showTime());
			postDelayed(this, 1000);
		} else {
			removeCallbacks(this);
		}
	}

}

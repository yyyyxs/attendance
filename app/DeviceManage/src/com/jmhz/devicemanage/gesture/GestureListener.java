package com.jmhz.devicemanage.gesture;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class GestureListener implements OnGestureListener {
	
	private OnFlingListener listener = null;

	public GestureListener(OnFlingListener listener) {
		if(listener == null){
			throw new IllegalArgumentException("OnFlingListener can not be null.");
		}
		this.listener = listener;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > 120) {
			listener.onFlingLeft();
			return true;
		} else if (e1.getX() - e2.getX() < -120) {
			listener.onFlingRight();
			return true;
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

}

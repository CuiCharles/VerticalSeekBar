package com.charles.www.verticalseekbar;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
/**
 * VerticalSeekBar
 * @description 
 * @author cuiwenju
 * @date 2016-11-2
 */
public class VerticalSeekBarContainer extends RelativeLayout {
	
	private final String TAG = this.getClass().getSimpleName();
	
 
	private GestureDetector mGestureDetector = null;
	private Context context;
	private VerticalSeekBar seekbar = null;
	private RelativeLayout seekBarContainer = null;
	
	
	private DisappearHandler mHandler = new DisappearHandler();
	
	
	private class DisappearHandler extends Handler
	{
		@Override
		public void handleMessage(Message msg) {
			setVisibilityOfLayout(View.GONE);
		}
	}
	
	private Timer  disappearTimer;
	
	private class DisappearTimerTask extends TimerTask{

		@Override
		public void run() {
			mHandler.sendEmptyMessage(0);
		}
		
	}
	
	
	/**
	 * 用来感知手势的内部类接口
	 */
	private class TGestureListener implements GestureDetector.OnGestureListener
	{

		@Override
		public boolean onDown(MotionEvent e) {
			//Log.d(TAG,">>>>>onDown");
			return true;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			//Log.d(TAG,">>>>>onShowPress");
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			//Log.d(TAG,">>>>>onSingleTapUp");
			return true;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			//Log.d(TAG,">>>>>onScroll");
			setVisibilityOfLayout(View.VISIBLE);
			traceSeekBar(distanceY);
			
			
			return true;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			//Log.d(TAG,">>>>>onLongPress");
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			//Log.d(TAG,">>>>>onFling");
			return true;
		}
		
	}
	
	private void traceSeekBar(float distanceY)
	{
		if(seekbar == null)
		{
			return;
		}
		float percentOfScreen = distanceY /100.f /8.f;
		float nowProgress = seekbar.getProgress();
		int newProgress = (int)(nowProgress + 100.f * percentOfScreen);
		if(newProgress > 100)
		{
			newProgress = 100;
		}
		else if(newProgress < 0)
		{
			newProgress = 0;
		}
			
		seekbar.setProgress((int)newProgress);
	}
	
	public VerticalSeekBarContainer(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initRelativeModel(context);
	}

	public VerticalSeekBarContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		initRelativeModel(context);
	}

	public VerticalSeekBarContainer(Context context) {
		super(context);
		initRelativeModel(context);
	}
	
	void initRelativeModel(Context context)
	{
		this.context = context;
		mGestureDetector = new GestureDetector(this.context,new TGestureListener());
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_UP://在用户手势离开后，开始计时，一定时间后，seekbar消失
				disappearTimer = new Timer();
				disappearTimer.schedule(new DisappearTimerTask(), 2000L);
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			case MotionEvent.ACTION_CANCEL:
				
				break;

			default:
				break;
		}
		return mGestureDetector.onTouchEvent(event);
	}
	
	
	public void setSeekbar(VerticalSeekBar seekbar) {
		this.seekbar = seekbar;
	}

	private void setVisibilityOfLayout(int visibility)
	{
		if(seekBarContainer != null)
		{
			seekBarContainer.setVisibility(visibility);
		}
	}
	
	public RelativeLayout getSeekBarContainer() {
		return seekBarContainer;
	}

	public void setSeekBarContainer(RelativeLayout seekBarContainer) {
		this.seekBarContainer = seekBarContainer;
	}
	
	
	

}

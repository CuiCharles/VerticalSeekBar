package com.charles.www.verticalseekbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
/**
 * VerticalSeekBar的封装类
 * @description 
 * @author cuiwenju
 * @date 2016-11-2
 */
public class VerticalSeekBarContainer extends RelativeLayout {

	private Context context;
	private VerticalSeekBar seekbar = null;
	private float actionDownYPosition;
	
	public VerticalSeekBarContainer(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public VerticalSeekBarContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public VerticalSeekBarContainer(Context context) {
		super(context);
		this.context = context;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			actionDownYPosition = event.getY();
			break;
		 case MotionEvent.ACTION_MOVE:
			 traceSeekBar(event.getY());
			 break;
         case MotionEvent.ACTION_UP:
			break;
         case MotionEvent.ACTION_CANCEL:
        	 
			break;
		}
		return true;
	}
	
	/**
	 * 用来跟踪滑动轨迹，拖动seekbar移动
	 * @param y
	 */
	private void traceSeekBar(float y)
	{
		if(seekbar == null)
			return;
		float layoutHeight = (float)getHeight();
		//当前滑动的距离
		float distance = -( y - actionDownYPosition);
		//当前移动的轨迹占layout高度的百分比再缩小一个参数
		float progress = (float) (distance / layoutHeight / 2.0f);
		int nowPro = seekbar.getProgress();
		int newPro = (int) (nowPro + (100*progress));
		if(newPro > 100)
		{
			newPro = 100;
		}else if(newPro < 0)
		{
			newPro = 0;
		}
		seekbar.setProgress(newPro);
	}

	public void setSeekbar(VerticalSeekBar seekbar) {
		this.seekbar = seekbar;
	}

}

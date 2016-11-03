package com.charles.www.verticalseekbar;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class VerticalSeekBar extends SeekBar {

	public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public VerticalSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public VerticalSeekBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }
	
	@Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }
	
	 protected void onDraw(Canvas c) {
	        //将SeekBar转转90度
	        c.rotate(-90);
	        //将旋转后的视图移动回来
	        c.translate(-getHeight(),0);
	        
	        super.onDraw(c);
	 }
	 
	 
	 @Override
	    public boolean onTouchEvent(MotionEvent event) {
		 boolean mIsUserSeekable=true;  
	        try  
	        {  
	            Field mIsUserSeekable_f = this.getClass().getSuperclass().getDeclaredField("mIsUserSeekable");  
	            mIsUserSeekable_f.setAccessible(true);  
	  
	            mIsUserSeekable = mIsUserSeekable_f.getBoolean(this);  
	        }  
	        catch (Exception e1)  
	        {  
	            e1.printStackTrace();  
	        }  
	          
	        if (!mIsUserSeekable || !isEnabled()) {  
	            return false;  
	        }  
	 
	        switch (event.getAction()) {
	            case MotionEvent.ACTION_DOWN:
	            case MotionEvent.ACTION_MOVE:
	            case MotionEvent.ACTION_UP:
	                int i=0;
	                //获取滑动的距离
	                i=getMax() - (int) (getMax() * event.getY() / getHeight());
	                //设置进度
	                setProgress(i);
	                //每次拖动SeekBar都会调用
	                onSizeChanged(getWidth(), getHeight(), 0, 0);
	                break;
	            case MotionEvent.ACTION_CANCEL:
	                break;
	        }
	        return true;
	    }
	 
	 @Override
	public synchronized void setProgress(int progress) {
		// TODO Auto-generated method stub
		super.setProgress(progress);
		onSizeChanged(getWidth(),getHeight(), 0, 0);
		
	}

}

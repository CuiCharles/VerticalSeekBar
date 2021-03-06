package com.charles.www.verticalseekbar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends ActionBarActivity {
	
	private final String TAG = this.getClass().getSimpleName();
	
	private VerticalSeekBar seekBar = null;
	private VerticalSeekBarContainer container = null;
	private RelativeLayout seekBarLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        seekBar = (VerticalSeekBar)findViewById(R.id.test_seekbar);
        container = (VerticalSeekBarContainer)findViewById(R.id.test_container);
        seekBarLayout = (RelativeLayout)findViewById(R.id.test_relative_layout);
        
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.d(TAG,"onStopTrackingTouch");
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.d(TAG,"onStartTrackingTouch");
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				Log.d(TAG,"onProgressChanged : progress = " + progress + ", fromUser = " + fromUser);
				
			}
			
		
		});
        
        container.setSeekbar(seekBar);
        seekBarLayout.setVisibility(View.GONE);
        container.setSeekBarContainer(seekBarLayout);
        seekBar.setMax(100);
        seekBar.setProgress(50);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

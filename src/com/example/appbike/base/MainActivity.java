package com.example.appbike.base;

import com.example.appbike.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	protected FrameLayout frameBase;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.telabase);
		
		frameBase = (FrameLayout)findViewById(R.id.frame);
		
	}
}

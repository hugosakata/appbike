package com.example.appbike.base;

import com.example.appbike.R;
import com.example.appbike.activities.ActivityConfig;
import com.example.appbike.activities.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TelaBase extends Activity {

	protected FrameLayout base;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.telabase);
		
		base = (FrameLayout)findViewById(R.id.frame);
		
		ImageView imgConfig = (ImageView)findViewById(R.id.imgConfig);
		imgConfig.setOnClickListener(clickConfig);
		
		ImageView imgHome = (ImageView)findViewById(R.id.imgHome);
		imgHome.setOnClickListener(clickHome);
	}
	
	private OnClickListener clickHome = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(TelaBase.this, Home.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
	};
	
	private OnClickListener clickConfig = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(TelaBase.this,
					ActivityConfig.class);
			startActivity(intent);
			finish();
		}
	};
}

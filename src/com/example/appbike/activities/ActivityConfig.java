package com.example.appbike.activities;

import com.example.appbike.R;
import com.example.appbike.base.TelaBase;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class ActivityConfig extends TelaBase{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LinearLayout linear = (LinearLayout)View.inflate(this, R.layout.config, null);
        base.addView(linear, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
	}
	
}

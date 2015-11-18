package com.example.appbike.activities;

import java.io.Serializable;

import com.example.appbike.R;
import com.example.appbike.base.Service;
import com.example.appbike.base.TelaBase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class ActivityServices extends TelaBase{

	private Service service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		Serializable objeto = intent.getSerializableExtra("service");
		if (objeto !=null)
			service = (Service) objeto;
		
		LinearLayout linear = (LinearLayout)View.inflate(this, R.layout.services, null);
        base.addView(linear, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
	}
	
}

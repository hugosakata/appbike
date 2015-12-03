package com.example.appbike.activities;

import java.io.File;

import com.example.appbike.R;
import com.example.appbike.base.TelaBase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class ActivityConfig extends TelaBase{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LinearLayout linear = (LinearLayout)View.inflate(this, R.layout.config, null);
        base.addView(linear, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
        title.setText("Configuração");
        
        
        Button btnLogoff = (Button)findViewById(R.id.btnLogoff);
        btnLogoff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				File f = new File(common.defaultpath + "register.json");
				if (f.exists())
					f.delete();
				
				login();
			}
		});
	}
	
}

package com.example.appbike.activities;

import java.io.Serializable;

import com.example.appbike.R;
import com.example.appbike.base.Service;
import com.example.appbike.base.TelaBase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class ActivityLoja extends TelaBase{

	//o servico que originou o chamado a esta tela, deve ser armazenado pois quando 
	//clicar no botao "mostrar no mapa" deve mostrar no mapa tds as lojas que prestam aquele msm serviço
	private Service service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		Serializable objeto = intent.getSerializableExtra("service");
		if (objeto !=null)
			service = (Service) objeto;
		
		LinearLayout linear = (LinearLayout)View.inflate(this, R.layout.loja, null);
        base.addView(linear, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
	}
	
	private OnClickListener clickCall = new OnClickListener() {
		public void onClick(View v) {
			//Listar os telefones da loja
		}
	};
	
	private OnClickListener clickShowMap = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(ActivityLoja.this,
					ActivityGps.class);
			intent.putExtra("service", service);
			startActivity(intent);
			finish();
		}
	};
	
}

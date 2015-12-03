package com.example.appbike.activities;

import java.io.Serializable;

import com.example.appbike.R;
import com.example.appbike.base.TelaBase;
import com.example.appbike.classes.Service;
import com.example.appbike.classes.Store;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//mostra todas as informacoes detalhadas da loja escolhida

public class ActivityStoreDetails extends TelaBase {

	// o servico que originou o chamado a esta tela, deve ser armazenado pois
	// quando
	// clicar no botao "mostrar no mapa" deve mostrar no mapa tds as lojas que
	// prestam aquele msm serviço
	private Store store;
	private int service_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		service_id = intent.getIntExtra("service_id", -1);

		Serializable store = intent.getSerializableExtra("store");
		if (store != null)
			this.store = (Store) store;

		LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.loja, null);
		base.addView(linear, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

		title.setText(this.store.getName());

		TextView txtName = (TextView) findViewById(R.id.txtName);
		TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
		TextView txtAddress = (TextView) findViewById(R.id.txtAddress);

		txtName.setText(this.store.getName());
		txtDescription.setText(this.store.getDescription());
		txtAddress.setText(this.store.getLocalization().getAddress());

		Button btnCall = (Button) findViewById(R.id.btnCall);
		btnCall.setOnClickListener(clickCall);

		Button btnShowMap = (Button) findViewById(R.id.btnShowMap);
		btnShowMap.setOnClickListener(clickShowMap);

	}

	private OnClickListener clickCall = new OnClickListener() {
		public void onClick(View v) {

			CharSequence[] cs = store.getPhone().toArray(new CharSequence[store.getPhone().size()]);

			AlertDialog.Builder builder = new AlertDialog.Builder(ActivityStoreDetails.this);
			builder.setTitle("Ligar").setItems(cs, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

				}
			});

			builder.show();
		}
	};

	private OnClickListener clickShowMap = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(ActivityStoreDetails.this, ActivityStoresGps.class);
			intent.putExtra("service_id", service_id);
			startActivity(intent);
		}
	};

}

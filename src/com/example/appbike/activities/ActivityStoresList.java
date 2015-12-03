package com.example.appbike.activities;

import java.io.Serializable;
import java.util.List;

import com.example.appbike.R;
import com.example.appbike.adapters.ServiceAdapter;
import com.example.appbike.adapters.StoreAdapter;
import com.example.appbike.base.TelaBase;
import com.example.appbike.classes.Service;
import com.example.appbike.classes.Store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

//lista todas as lojas monstrando em uma lista

public class ActivityStoresList extends TelaBase {

	private Service service;
	private List<Store> stores;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		int service_id = intent.getIntExtra("service_id", -1);
		if (service_id >= 0) {
			service = common.getServicebyId(service_id);
			if (service != null) {
				LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.services, null);
				base.addView(linear, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

				title.setText(service.getName());
 
				stores = common.getStoresbyService(service_id);
				
				ListView listStores = (ListView) findViewById(R.id.listStores);
				listStores.setAdapter(new StoreAdapter(this, R.layout.item, stores));
				listStores.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Intent intent = new Intent(ActivityStoresList.this, ActivityStoreDetails.class);
						intent.putExtra("service_id", service.getId());
						intent.putExtra("store", stores.get(position));
						startActivity(intent);
					}
				});
				
				
				ImageView imgGps = (ImageView)findViewById(R.id.imgGps);
				imgGps.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(ActivityStoresList.this, ActivityStoresGps.class);
						intent.putExtra("service_id", service.getId());
						startActivity(intent);
						ActivityStoresList.this.finish();
					}
				});
				
			}
		}

	}

}

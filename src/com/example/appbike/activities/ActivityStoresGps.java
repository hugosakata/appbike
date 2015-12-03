package com.example.appbike.activities;

import java.util.List;

import com.example.appbike.R;
import com.example.appbike.base.TelaBase;
import com.example.appbike.classes.Service;
import com.example.appbike.classes.Store;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

//lista todos as lojas mostrando no mapa

public class ActivityStoresGps extends TelaBase implements LocationListener {

	private static final long LOCATION_REFRESH_DISTANCE = 1; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long LOCATION_REFRESH_TIME = 1; // 1 minute
	
	static LatLng ME = new LatLng(-23.573869, -46.632438);
	// static final LatLng KIEL = new LatLng(53.551, 9.993);
	static final LatLng KIEL = new LatLng(-23.573817, -46.621037);

	private LocationManager locationManager;
	private String provider;

	private Service service;
	private GoogleMap map;

	private Marker me;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		final int service_id = intent.getIntExtra("service_id", -1);
		if (service_id >= 0) {
			service = common.getServicebyId(service_id);
			if (service != null) {

				LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.gps, null);
				base.addView(linear, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

				title.setText(service.getName());

				ImageView imgList = (ImageView) findViewById(R.id.imgList);
				imgList.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(ActivityStoresGps.this, ActivityStoresList.class);
						intent.putExtra("service_id", service.getId());
						startActivity(intent);
						ActivityStoresGps.this.finish();
					}
				});

				map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
				map.getUiSettings().setZoomControlsEnabled(true);
				map.setMyLocationEnabled(true);
				
				locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
			            LOCATION_REFRESH_DISTANCE, this);
				
				Criteria criteria = new Criteria();
				provider = locationManager.getBestProvider(criteria, false);
				Location location = locationManager.getLastKnownLocation(provider);

				if (location != null) {
					onLocationChanged(location);
				}
				else
				{
					MarkerMe();
					Toast.makeText(this, "Sinal de GPS não localizado, aguarde...", Toast.LENGTH_LONG).show();
				}
				
				List<Store> stores = common.getStores();
				for (int i = 0; i < stores.size(); i++) {
					double latitude = Double.parseDouble(stores.get(i).getLocalization().getLatitude());
					double longitude = Double.parseDouble(stores.get(i).getLocalization().getLongitude());
					LatLng store_latlong = new LatLng(latitude, longitude);
					map.addMarker(new MarkerOptions().position(store_latlong));
				}

				map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

			}
		}

	}

	private void MarkerMe() {

		if (me !=null)
			me.remove();
		
		BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.drawable.marker_gps);
		Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.marker_gps);

		me = map.addMarker(new MarkerOptions().position(ME)
				.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(imageBitmap, 72, 72, true))));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(ME, 15));
	}

	@Override
	protected void onResume() {
		super.onResume();
		//locationManager.requestLocationUpdates(provider, 1, 1, this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		double lat = (double) (location.getLatitude());
		double lng = (double) (location.getLongitude());
		ME = new LatLng(lat, lng);
		MarkerMe();
		Toast.makeText(this, "atualizando", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}
}
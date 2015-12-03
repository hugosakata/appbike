package com.example.appbike.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.appbike.base.Common;

import android.util.Log;

public class Load {

	private Common common;

	public Load(Common common) throws JSONException {

		this.common = common;
		clear();
		loadServices();
		loadLojas();
	}

	private void loadServices() throws JSONException {
		String services = ReadFromfile("servicos.json");

		try {
			JSONObject my_obj = new JSONObject(services);
			JSONArray services_list = my_obj.getJSONArray("services");

			for (int i = 0; i < services_list.length(); i++) {
				JSONObject service_json = (JSONObject) services_list.get(i);
				Service service = new Service();
				service.setId(service_json.getInt("id"));
				service.setName(service_json.getString("nome"));
				service.setDescription(service_json.getString("descricao"));
				service.setPhoto(service_json.getString("foto"));

				common.getServices().add(service);
			}
		} catch (Exception e) {
			Log.e(common.applicationname, e.getMessage());
		}

	}

	private void loadLojas() {
		String string_lojas = ReadFromfile("lojas.json");
				
		try {
			JSONObject my_obj = new JSONObject(string_lojas);
			JSONArray stores_list = my_obj.getJSONArray("lojas");

			for (int i = 0; i < stores_list.length(); i++) {
				JSONObject store_json = (JSONObject) stores_list.get(i);
				Store store = new Store();
				store.setId(store_json.getInt("id"));
				store.setName(store_json.getString("nome"));
				store.setDescription(store_json.getString("descricao"));

				store.setPhone(getPhones(store_json));
				store.setPhoto(getPhotos(store_json));

				store.setLocalization(getLocation(store_json));

				store.setServices(getServices(store_json));

				common.getStores().add(store);
			}
		} catch (Exception e) {
			Log.e(common.applicationname, e.getMessage());
		}
	}

	public String ReadFromfile(String fileName) {
		StringBuilder returnString = new StringBuilder();
		InputStream fIn = null;
		InputStreamReader isr = null; 
		BufferedReader input = null;
		try {
			fIn = common.getResources().getAssets().open(fileName, common.MODE_WORLD_READABLE);
			isr = new InputStreamReader(fIn);
			input = new BufferedReader(isr);
			String line = "";
			while ((line = input.readLine()) != null) {
				returnString.append(line);
			}
		} catch (Exception e) {
			e.getMessage(); 
		} finally {
			try {
				if (isr != null)
					isr.close();
				if (fIn != null)
					fIn.close();
				if (input != null)
					input.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
		return returnString.toString();
	}

	private void clear() {
		common.getServices().clear();
		common.getStores().clear();
	}

	private List<String> getPhones(JSONObject store_json) throws JSONException {
		List<String> phones = new ArrayList<String>();
		JSONArray phone_list = store_json.getJSONArray("telefones");
		for (int j = 0; j < phone_list.length(); j++) {
			phones.add((String) phone_list.get(j));
		}
		return phones;
	}

	private List<String> getPhotos(JSONObject store_json) throws JSONException {
		List<String> photos = new ArrayList<String>();
		JSONArray photo_list = store_json.getJSONArray("fotos");
		for (int j = 0; j < photo_list.length(); j++) {
			photos.add((String) photo_list.get(j));
		}
		return photos;
	}

	private Localization getLocation(JSONObject store_json) throws JSONException {
		Localization localization = new Localization();

		JSONObject localization_json = (JSONObject) store_json.getJSONObject("localizacao");

		localization.setAddress(localization_json.getString("endereco"));
		localization.setLatitude(localization_json.getString("latitude"));
		localization.setLongitude(localization_json.getString("longetude"));

		return localization;
	}

	private List<Service> getServices(JSONObject store_json) throws JSONException {
		List<Service> services = new ArrayList<Service>();
		JSONArray service_list = store_json.getJSONArray("servicos");
		for (int j = 0; j < service_list.length(); j++) {
			Service service = common.getServicebyId(service_list.getInt(j));
			if (service != null) {
				services.add(service);
			}
		}

		return services;
	}

}

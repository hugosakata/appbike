package com.example.appbike.base;

import java.util.ArrayList;
import java.util.List;

import com.example.appbike.classes.Service;
import com.example.appbike.classes.Store;

import android.app.Application;

public class Common extends Application {

	public static final String defaultpath = "/sdcard/AppBike/";
	public static final String applicationname = "appbike";

	private List<Service> services;
	private List<Store> stores;

	public List<Service> getServices() {
		if (services == null)
			services = new ArrayList<Service>();
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Service getServicebyId(int id) {
		Service service = null;
		for (int i = 0; i < services.size(); i++) {
			if (services.get(i).getId() == id){
				service = services.get(i);
				break;
			}
		}
		return service;
	}

	public List<Store> getStores() {
		if (stores == null)
			stores = new ArrayList<Store>();
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public List<Store> getStoresbyService(int service_id){
		   
	    List<Store> store = new ArrayList<Store>();   

	    for(int i=0; i < stores.size(); i++){
	       
	        List<Service> services = stores.get(i).getServices();
	        for(int j=0; j < services.size(); j++){
	            if(services.get(j).getId() == service_id)
	                store.add(stores.get(i));
	        }
	    }

	    return store;
	}
	
}

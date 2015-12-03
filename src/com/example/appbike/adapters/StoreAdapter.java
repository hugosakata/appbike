package com.example.appbike.adapters;

import java.util.List;

import com.example.appbike.R;
import com.example.appbike.activities.ActivityStoreDetails;
import com.example.appbike.activities.ActivityStoresList;
import com.example.appbike.base.Common;
import com.example.appbike.classes.Service;
import com.example.appbike.classes.Store;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreAdapter extends ArrayAdapter<Store> {

	private Context context;
	private Common common;
	private int textViewResourceId;
	private List<Store> stores;

	public StoreAdapter(Context context, int textViewResourceId, List<Store> stores) {
		super(context, textViewResourceId, stores);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.stores = stores;
		common = (Common) context.getApplicationContext();
	}

	// bm = BitmapFactory.decodeFile(TelaBase.caminhopadrao +
	// "EduBar/ICONS/foto_padrao.png");
	// imagens.setBitmap(holder.objeto.getStrPathIcone(),
	// Bitmap.createScaledBitmap(bm, 200, 200, true));
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		View v = convertView;

		try {
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(textViewResourceId, null);

				holder = new ViewHolder();
				holder.store = stores.get(position);
				
				//holder.img = (ImageView) v.findViewById(R.id.icone);
				holder.textName = (TextView) v.findViewById(R.id.textName);
				holder.textDescription = (TextView) v.findViewById(R.id.textDescription);
				holder.context = this.context;

				v.setTag(holder);
			} else {
				holder = (ViewHolder) v.getTag();
				holder.store = stores.get(position);
			}
			if (holder.store != null) {

				if (holder.textName != null) {
					holder.textName.setText(holder.store.getName());
					holder.textName.setTag(holder.store.getId());
				}

				if (holder.textDescription != null) {
					holder.textDescription.setText(holder.store.getDescription());
					holder.textDescription.setTag(holder.store.getId());
				}

//				if (holder.img != null) {
//					Bitmap bm = BitmapFactory.decodeFile(common.defaultpath + holder.service.getPhoto());
//					holder.img.setImageBitmap(Bitmap.createScaledBitmap(bm, 200, 200, true));
//					holder.img.setTag(holder.service.getId());
//				}
			}


		} catch (Exception exc) {
		}
		return v;
	}

	static class ViewHolder {

		Store store;
		ImageView img;
		TextView textName;
		TextView textDescription;
		Context context;
	}

}

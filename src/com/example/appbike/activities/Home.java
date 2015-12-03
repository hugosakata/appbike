
package com.example.appbike.activities;

import org.json.JSONException;

//alem de ser a pagina inicial do sistema
//tambem mostrará todos os serviços disponiveis cadastrados

import com.example.appbike.R;
import com.example.appbike.adapters.ServiceAdapter;
import com.example.appbike.base.TelaBase;
import com.example.appbike.classes.Load;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Home extends TelaBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.home, null);
		base.addView(linear, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

		try {
			new Load(common);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		ListView listServices = (ListView) findViewById(R.id.listServices);
		listServices.setAdapter(new ServiceAdapter(this, R.layout.item, common.getServices()));

	}

}

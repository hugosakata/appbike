package com.example.appbike.activities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.appbike.R;
import com.example.appbike.base.Common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityRegister extends Activity {

	EditText edtUser, edtPassword, edtRim;

	Common common;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		common = ((Common) getApplicationContext());

		edtUser = (EditText) findViewById(R.id.edtUser);
		edtPassword = (EditText) findViewById(R.id.edtPassword);
		edtRim = (EditText) findViewById(R.id.edtRim);

		Button save = (Button) findViewById(R.id.btnsave);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!edtUser.getText().toString().equals("") 
						&& !edtPassword.getText().toString().equals("")
						&& !edtRim.getText().toString().equals(""))
					if (save()) {
						Intent intent = new Intent(ActivityRegister.this, Home.class);
						startActivity(intent);
						finish();
					} else
						Toast.makeText(ActivityRegister.this, "Falha ao gravar", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(ActivityRegister.this, "Campos obrigatórios vazios", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private boolean save() {
		boolean resp = false;

		JSONObject json = new JSONObject();
		try {
			json.put("user", edtUser.getText().toString());
			json.put("password", edtPassword.getText().toString());
			json.put("rim", edtRim.getText().toString());

			String strPathRegister = common.defaultpath;

			try {
				
				File f = new File(strPathRegister);
				f.mkdir();
				
				FileWriter file = new FileWriter(strPathRegister + "register.json");
				file.write(json.toString());
				file.close();
				resp = true;

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return resp;
	}

}

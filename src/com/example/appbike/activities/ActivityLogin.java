package com.example.appbike.activities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.appbike.R;
import com.example.appbike.base.Common;
import com.example.appbike.base.TelaBase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLogin extends Activity {

	EditText edtUser, edtPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		edtUser = (EditText) findViewById(R.id.edtLogin);
		edtPassword = (EditText) findViewById(R.id.edtPassword);

		TextView txtFirstAccess = (TextView) findViewById(R.id.txtFirsAccess);
		txtFirstAccess.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
				startActivity(intent);
				finish();
			}
		});

		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				File f = new File(Common.defaultpath + "register.json");
				if (f.exists()) {
					JSONObject my_obj;
					try {
						my_obj = new JSONObject(ReadFromfile("register.json"));
						if (edtUser.getText().toString().equals(my_obj.getString("user"))
								&& edtPassword.getText().toString().equals(my_obj.getString("password"))) {
							Intent intent = new Intent(ActivityLogin.this, Home.class);
							startActivity(intent);
							finish();
						} else
							Toast.makeText(ActivityLogin.this, "Usuário e senha inválidos", Toast.LENGTH_SHORT).show();

					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else
					Toast.makeText(ActivityLogin.this, "Cadastro não realizado", Toast.LENGTH_SHORT).show();
			}
		});
	}

	public String ReadFromfile(String fileName) {

		File file = new File(Common.defaultpath + fileName);

		// Read text from file
		StringBuilder text = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				text.append(line);
				text.append('\n');
			}
			br.close();
		} catch (IOException e) {
			// You'll need to add proper error handling here
		}

		return text.toString();
	}

}

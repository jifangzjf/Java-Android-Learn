package com.example.loginproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText account = null;
	private EditText passwd = null;
	private CheckBox remember = null;
	private Button login = null;

	private SharedPreferences.Editor editor = null;
	private SharedPreferences pref = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		pref = getPreferences(Context.MODE_PRIVATE);

		account = (EditText) findViewById(R.id.account);
		passwd = (EditText) findViewById(R.id.passwd);
		remember = (CheckBox) findViewById(R.id.remember);
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(this);

		boolean isRemembered = pref.getBoolean("remember", false);
		if (isRemembered) {
			String accountContent = pref.getString("account", "");
			String passwdContent = pref.getString("passwd", "");

			account.setText(accountContent);
			passwd.setText(passwdContent);
			remember.setChecked(true);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			String accountContent = account.getText().toString();
			String passwdContent = passwd.getText().toString();

			if (accountContent.equals("admin")
					&& passwdContent.equals("123456")) {
				editor = pref.edit();
				if (remember.isChecked()) {
					editor.putBoolean("remember", true);
					editor.putString("account", accountContent);
					editor.putString("passwd", passwdContent);
				} else {
					editor.clear();
				}
				editor.commit();

				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(this, "account or password is invalid",
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}
}

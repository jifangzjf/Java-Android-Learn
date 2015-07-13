package com.example.activitytest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends BaseActivity implements OnClickListener {

	private Button button = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ActivityCollector.finishAll();
	}

	public static void startActivity(Context context, String data1, String data2) {
		Intent intent = new Intent(context, SecondActivity.class);
		intent.putExtra("param1", data1);
		intent.putExtra("param2", data2);
		context.startActivity(intent);
	}

}

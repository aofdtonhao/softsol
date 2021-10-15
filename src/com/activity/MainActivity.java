package com.activity;

import com.model.Model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Model.build(this);
	}

	public void onCultureClick(View button) {
		Intent intent = new Intent(this, CultureActivity.class);
		startActivity(intent);
	}

	public void onAgroforestClick(View button) {
		Intent intent = new Intent(this, AgroforestActivity.class);
		startActivity(intent);
	}

}

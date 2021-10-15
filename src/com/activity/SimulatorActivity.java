package com.activity;

import com.entities.Culture;
import com.utility.IconAdapter;
import com.utility.IconAdapterCultureEcoList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

public class SimulatorActivity extends Activity {
	// TODO Auto-generated constructor stub

	private GridView gridViewNative;
	private GridView gridViewEco;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.simulator);

	
		gridViewNative = (GridView) findViewById(R.id.gridView1);
		gridViewNative.setAdapter(new IconAdapter(this,Culture.NATIVE_CULTURE));

		gridViewEco = (GridView) findViewById(R.id.gridView2);
		gridViewEco.setAdapter(new IconAdapterCultureEcoList(this,Culture.ECONOMIC_CULTURE));
	}

}

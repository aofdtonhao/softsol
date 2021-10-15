package com.activity;

import java.util.List;

import com.application.AgroforestApplication;
import com.entities.Agroforest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AgroforestActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.agroforest);
		createList();
	}

	public void createList() {
		List<String> agroforestList = AgroforestApplication.getInstance()
				.retrieveAll();
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, agroforestList);
		setListAdapter(adapter);
	}

	protected void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		Object item = getListAdapter().getItem(position);
		Agroforest agroforest = AgroforestApplication.getInstance()
				.retrieveByName(item.toString());

		Intent intent = new Intent(this, AddAgroforestActivity.class);
		intent.putExtra("id", agroforest.getId());
		intent.putExtra("name", agroforest.getName());
		startActivity(intent);
	}

	public void onBackClick(View button) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void onAddAgroforestClick(View button) {
		Intent intent = new Intent(this, AddAgroforestActivity.class);
		intent.putExtra("addNew", "cadastrar");
		startActivity(intent);
	}

}

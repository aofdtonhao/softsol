package com.activity;

import java.util.List;

import com.application.CultureApplication;
import com.entities.Culture;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CultureActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.culture);
		createList();
	}

	public void createList() {
		List<String> listCulture = CultureApplication.getInstance()
				.retrieveAll();
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, listCulture);
		setListAdapter(adapter);
	}

	protected void onListItemClick(ListView list, View view, int position,
			long id) {
		super.onListItemClick(list, view, position, id);

		Object item = getListAdapter().getItem(position);
		Culture culture = CultureApplication.getInstance().retrieveByName(item.toString());

		Intent intent = new Intent(this, AddCultureActivity.class);
		intent.putExtra("id", culture.getId());
		intent.putExtra("name", culture.getName());
		intent.putExtra("height", String.valueOf(culture.getArea().getHeight()));
		intent.putExtra("treetopWidth",String.valueOf(culture.getTreetop().getArea().getWidth()));
		intent.putExtra("treetopHeight",String.valueOf(culture.getTreetop().getArea().getHeight()));
		intent.putExtra("type", culture.getType());
		startActivity(intent);
	}

	public void onBackClick(View button) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void onAddCultureClick(View button) {
		Intent intent = new Intent(this, AddCultureActivity.class);
		intent.putExtra("addNew", "cadastrar");
		startActivity(intent);
	}

}

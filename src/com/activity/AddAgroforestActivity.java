package com.activity;

import java.util.ArrayList;

import com.application.AgroforestApplication;
import com.utility.DialogUtility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class AddAgroforestActivity extends Activity {

	private int agroforestId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_agroforest);

		Bundle bundle = getIntent().getExtras();
		if (!bundle.containsKey("addNew")) {
			agroforestId = bundle.getInt("id");
			Button save = (Button) findViewById(R.id.save_agroforest);
			save.setVisibility(View.GONE);
			AutoCompleteTextView name = (AutoCompleteTextView) findViewById(R.id.agroforest_name);
			name.setText(bundle.getString("name"));
		} else {
			Button enter = (Button) findViewById(R.id.enter_agroforest);
			Button update = (Button) findViewById(R.id.update_agroforest);
			Button delete = (Button) findViewById(R.id.delete_agroforest);
			enter.setVisibility(View.GONE);
			update.setVisibility(View.GONE);
			delete.setVisibility(View.GONE);
		}
	}

	public void onSaveClick(View button) {
		ArrayList<String> values = getTextViewValues();
		AgroforestApplication.getInstance().add(values);

		Bundle args = new Bundle();
		args.putString(DialogUtility.MESSAGE, "Agrofloresta \"" + values.get(0)
				+ "\" cadastrada com sucesso");
		showDialog(DialogUtility.OK, args);
	}

	public void onUpdateClick(View button) {
		ArrayList<String> values = getTextViewValues();
		AgroforestApplication.getInstance().update(agroforestId, values);

		Bundle args = new Bundle();
		args.putString(DialogUtility.MESSAGE, "Agrofloresta \"" + values.get(0)
				+ "\" alterada com sucesso");
		showDialog(DialogUtility.OK, args);
	}

	private ArrayList<String> getTextViewValues() {
		AutoCompleteTextView name = (AutoCompleteTextView) findViewById(R.id.agroforest_name);

		ArrayList<String> textViewValues = new ArrayList<String>();
		textViewValues.add(name.getText().toString());
		return textViewValues;
	}

	public void onDeleteClick(View button) {
		AutoCompleteTextView name = (AutoCompleteTextView) findViewById(R.id.agroforest_name);

		Bundle args = new Bundle();
		args.putString(DialogUtility.MESSAGE,
				"Deseja excluir a agrofloresta \"" + name.getText().toString()
						+ "\"?");
		showDialog(DialogUtility.YES_NO, args);
	}

	public void delete() {
		AgroforestApplication.getInstance().delete(agroforestId);

		AutoCompleteTextView name = (AutoCompleteTextView) findViewById(R.id.agroforest_name);

		Bundle args = new Bundle();
		args.putString(DialogUtility.MESSAGE, "Agrofloresta \""
				+ name.getText().toString() + "\" excluida com sucesso");
		showDialog(DialogUtility.OK, args);
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.app_name)
				.setMessage(args.getString(DialogUtility.MESSAGE))
				.setCancelable(false);
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				AddAgroforestActivity.this.dialogResponse(id);
			}
		};
		switch (id) {
		case DialogUtility.OK:
			builder.setNeutralButton("Fechar", dialogClickListener);
			break;
		case DialogUtility.YES_NO:
			builder.setPositiveButton("Sim", dialogClickListener)
					.setNegativeButton("Nao", dialogClickListener);
			break;
		}
		return builder.create();
	}

	public void dialogResponse(int id) {
		switch (id) {
		case DialogInterface.BUTTON_NEUTRAL:
			back();
			break;
		case DialogInterface.BUTTON_POSITIVE:
			delete();
			break;
		}
	}

	public void onCancelClick(View button) {
		back();
	}

	public void back() {
		Intent intent = new Intent(this, AgroforestActivity.class);
		startActivity(intent);
	}

	public void onEnterClick(View button) {
		Intent intent = new Intent(this, SimulatorActivity.class);
		startActivity(intent);
	}

}

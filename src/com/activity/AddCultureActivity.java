package com.activity;

import java.util.ArrayList;

import com.application.CultureApplication;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddCultureActivity extends Activity {

	private int cultureId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_culture);

		Bundle bundle = getIntent().getExtras();
		if (!bundle.containsKey("addNew")) {
			cultureId = bundle.getInt("id");
			Button save = (Button) findViewById(R.id.save_culture);
			save.setVisibility(View.GONE);
			AutoCompleteTextView name = (AutoCompleteTextView) findViewById(R.id.culture_name);
			AutoCompleteTextView height = (AutoCompleteTextView) findViewById(R.id.height);
			AutoCompleteTextView treetopWidth = (AutoCompleteTextView) findViewById(R.id.treetopWidth);
			AutoCompleteTextView treetopHeight = (AutoCompleteTextView) findViewById(R.id.treetopHeight);

			System.out.println(bundle.getString("type"));
			
			RadioButton radioButton = null;
			String teste = "native";
			if(teste.compareTo(bundle.getString("type"))== 0){
				radioButton = (RadioButton) findViewById(R.id.nativeType);
			}else{
				radioButton = (RadioButton) findViewById(R.id.economicType);
			}
			radioButton.setChecked(true);  	
			
			name.setText(bundle.getString("name"));
			height.setText(bundle.getString("height"));
			treetopWidth.setText(bundle.getString("treetopWidth"));
			treetopHeight.setText(bundle.getString("treetopHeight"));
		} else {
			Button update = (Button) findViewById(R.id.update_culture);
			Button delete = (Button) findViewById(R.id.delete_culture);
			update.setVisibility(View.GONE);
			delete.setVisibility(View.GONE);
		}
	}

	public void onSaveClick(View button) {
		ArrayList<String> values = getTextViewValues();
		CultureApplication.getInstance().add(values);

		Bundle args = new Bundle();
		args.putString(DialogUtility.MESSAGE, "Cultura \"" + values.get(0)
				+ "\" cadastrada com sucesso");
		showDialog(DialogUtility.OK, args);
	}

	public void onUpdateClick(View button) {
		ArrayList<String> values = getTextViewValues();
		CultureApplication.getInstance().update(cultureId, values);

		Bundle args = new Bundle();
		args.putString(DialogUtility.MESSAGE, "Cultura \"" + values.get(0)
				+ "\" alterada com sucesso");
		showDialog(DialogUtility.OK, args);
	}

	private ArrayList<String> getTextViewValues() {
		AutoCompleteTextView name = (AutoCompleteTextView) findViewById(R.id.culture_name);
		AutoCompleteTextView height = (AutoCompleteTextView) findViewById(R.id.height);
		AutoCompleteTextView treetopWidth = (AutoCompleteTextView) findViewById(R.id.treetopWidth);
		AutoCompleteTextView treetopHeight = (AutoCompleteTextView) findViewById(R.id.treetopHeight);
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

		String type = null;
		if (radioGroup.getCheckedRadioButtonId() == 0x7f05000c) {
			type = "native";
		} else if (radioGroup.getCheckedRadioButtonId() == 0x7f05000d) {
			type = "economic";
		}

		ArrayList<String> textViewValues = new ArrayList<String>();
		textViewValues.add(name.getText().toString());
		textViewValues.add(height.getText().toString());
		textViewValues.add(treetopWidth.getText().toString());
		textViewValues.add(treetopHeight.getText().toString());
		textViewValues.add(type);
		return textViewValues;
	}

	public void onDeleteClick(View button) {
		AutoCompleteTextView name = (AutoCompleteTextView) findViewById(R.id.culture_name);

		Bundle args = new Bundle();
		args.putString(DialogUtility.MESSAGE, "Deseja excluir a cultura \""
				+ name.getText().toString() + "\"?");
		showDialog(DialogUtility.YES_NO, args);
	}

	public void delete() {
		CultureApplication.getInstance().delete(cultureId);

		AutoCompleteTextView name = (AutoCompleteTextView) findViewById(R.id.culture_name);

		Bundle args = new Bundle();
		args.putString(DialogUtility.MESSAGE, "Cultura \""
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
				AddCultureActivity.this.dialogResponse(id);
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

	public void back() {
		Intent intent = new Intent(this, CultureActivity.class);
		startActivity(intent);
	}

	public void onCancelClick(View button) {
		back();
	}

}

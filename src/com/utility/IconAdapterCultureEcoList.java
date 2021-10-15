package com.utility;

import java.util.List;

import com.activity.R;
import com.application.CultureApplication;
import com.entities.Culture;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class IconAdapterCultureEcoList extends BaseAdapter {

	private Activity activity;
    private List<Culture> cultureEconomicList;
    private String type;
    
    
    public IconAdapterCultureEcoList(Activity activity,String type) {
		this.activity = activity;
		this.type = type;
	}
    
	public int getCount() {
		cultureEconomicList = CultureApplication.getInstance().retrieveByType(type);	
		return cultureEconomicList.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
	
		TextView textView;
		if (convertView == null) {

			LayoutInflater inflater = activity.getLayoutInflater();
			convertView = inflater.inflate(R.layout.icongrideconomica, null);

			textView = (TextView) convertView.findViewById(R.id.iconCultureEcoText);
			Culture culture = cultureEconomicList.get(position);
			textView.setText(culture.getName());
		}

		return convertView;
	}

	
	
}

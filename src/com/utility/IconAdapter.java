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

public class IconAdapter extends BaseAdapter {

	private Activity activity;
	List<Culture> cultureNativeList;
	private String type;

	public IconAdapter(Activity activity, String type) {
		this.activity = activity;
		this.type = type;
	}

	public int getCount() {
		cultureNativeList = CultureApplication.getInstance().retrieveByType(type);
		return cultureNativeList.size();
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		TextView textView;
		if (convertView == null) {

			LayoutInflater inflater = activity.getLayoutInflater();
			convertView = inflater.inflate(R.layout.icongrid, null);

			textView = (TextView) convertView.findViewById(R.id.icon_text);
			Culture culture = cultureNativeList.get(position);
			textView.setText(culture.getName());
		}

		return convertView;
	}

}

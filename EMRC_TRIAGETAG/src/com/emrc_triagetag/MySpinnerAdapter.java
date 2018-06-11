package com.emrc_triagetag;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint({ "InflateParams", "ViewHolder" })
public class MySpinnerAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<String> unit;

	public MySpinnerAdapter(Context context, ArrayList<String> list) {
		this.context = context;
		this.unit = list;
	}

	// 清單
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return setView(position, convertView);
	}

	// 選完
	public View getView(int position, View convertView, ViewGroup parent) {
		return setView(position, convertView);
	}

	private View setView(int position, View convertView) {
		int result = R.layout.style_spinner_20;
		if (MainActivity.EN) {
			result = R.layout.style_spinner_30;
		}
		convertView = LayoutInflater.from(context).inflate(result, null);
		TextView tv = (TextView) convertView.findViewById(R.id.textview);
		int size = 20;
		if (MainActivity.EN) {
			size = 15;
			if (MainActivity.textSize == 40) {
				size = 25;
			}
		} else {
			if (MainActivity.textSize == 40) {
				size = 35;
			}
		}
		tv.setTextSize(size);
		tv.setText(getItem(position).toString());
		// Drawable dr =
		// context.getResources().getDrawable(R.drawable.gray_baseline);
		// dr.setBounds(0, 0, dr.getMinimumWidth(), dr.getMinimumHeight());
		// tv.setCompoundDrawables(null, null, null, dr);
		return convertView;
	}

	public int getCount() {
		return unit.size();
	}

	public Object getItem(int position) {
		return unit.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
}
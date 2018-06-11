package com.emrc_triagetag;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint({ "InflateParams", "ViewHolder" })
public class MySpinnerAdapter_MAIN extends BaseAdapter {
	private Context context;
	private ArrayList<String> unit;
	private ArrayList<Integer> leve;

	public MySpinnerAdapter_MAIN(Context context, ArrayList<String> list, ArrayList<Integer> leve) {
		this.context = context;
		this.unit = list;
		this.leve = leve;
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
		convertView = LayoutInflater.from(context).inflate(R.layout.style_spinner_25, null);
		TextView tv = (TextView) convertView.findViewById(R.id.textview);
		if (MainActivity.EN) {
			tv.setTextSize(15);
			if (MainActivity.textSize == 40) {
				tv.setTextSize(25);
			}
		} else {
			if (MainActivity.textSize == 40) {
				tv.setTextSize(35);
			}
		}
		switch (leve.get(position)) {
		case 1:
			tv.setTextColor(Color.WHITE);
			tv.setBackgroundColor(Color.RED);
			break;
		case 2:
			tv.setBackgroundColor(Color.YELLOW);
			break;
		case 3:
			tv.setBackgroundColor(Color.GREEN);
			break;
		default:
			break;
		}
		tv.setText(getItem(position).toString());
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
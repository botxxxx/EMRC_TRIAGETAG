package com.emrc_triagetag;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("InflateParams")
public class EMRCActivity extends Activity implements OnClickListener, OnItemClickListener {
	private int count = 0, select = 0;
	private ArrayList<String> Condition_list_string = new ArrayList<String>();
	public static ArrayList<View> view_list = new ArrayList<View>();
	private List<Boolean> Condition_list_click = new ArrayList<Boolean>();
	private ListView emrclist;
	private LinearLayout TopLinearLayout;
	private TextView emrc_title, emrc_no_down;
	private ImageView emrc_ok;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.emrc_view);
		findViewById();
		setCount();
		load();
		emrclist.post(new Runnable() {
			public void run() {
				// 定位至已勾選位置
				emrclist.setSelection(select);
			}
		});
	}

	public void onClick(View v) {
		save();
		finish();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			save();
			finish();
			return true;
		}
		return false;
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		int leve = MainActivity.leve_count;
		CheckedTextView chkItem = (CheckedTextView) view.findViewById(R.id.checkedTextView);
		switch (leve) {
		case 0:
			chkItem.setChecked(!chkItem.isChecked());
			Condition_list_click.set(position, chkItem.isChecked());
			break;
		case 1: // 紅
			if (position <= 9) {
				chkItem.setChecked(!chkItem.isChecked());
				Condition_list_click.set(position, chkItem.isChecked());
			}
			break;
		case 2: // 黃
			if (position <= 6) {
				chkItem.setChecked(!chkItem.isChecked());
				Condition_list_click.set(position, chkItem.isChecked());
			}
			break;
		}
	}

	private void save() {
		count = 0;
		for (int i = 0; i < Condition_list_click.size(); i++) {
			if (Condition_list_click.get(i)) {
				count += (1 << i);
			}
		}
		MainActivity.emrc_count = count;
		MainActivity.uploadview(this);
	}

	private void load() {
		view_list.clear();
		if (MainActivity.emrc_count > 0) {
			count = MainActivity.emrc_count;
			for (int i = 9; i >= 0; i--) {
				if (count >= (1 << i)) {
					count -= (1 << i);
					Condition_list_click.set(i, true);
					select = i;
				}
			}
		}
		LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for (int i = 0; i < Condition_list_click.size(); i++) {
			View vi = mInflater.inflate(R.layout.style_checked, null);
			CheckedTextView chkBshow = (CheckedTextView) vi.findViewById(R.id.checkedTextView);
			int leve = MainActivity.leve_count;
			switch (leve) {
			case 0: // 黑
				chkBshow.setText(Condition_list_string.get(i).toString());
				chkBshow.setChecked(Condition_list_click.get(i));
				break;
			case 1: // 紅
				if (i >= 10) {
					chkBshow.setEnabled(false);
					chkBshow.setVisibility(View.INVISIBLE);
				} else {
					chkBshow.setText(Condition_list_string.get(i).toString());
					chkBshow.setChecked(Condition_list_click.get(i));
				}
				break;
			case 2: // 黃
				if (i >= 7) {
					chkBshow.setEnabled(false);
					chkBshow.setVisibility(View.INVISIBLE);
				} else {
					chkBshow.setText(Condition_list_string.get(i).toString());
					chkBshow.setChecked(Condition_list_click.get(i));
				}
				break;
			}
			view_list.add(vi);
		}
		emrclist.setAdapter(new MyListAdapter(view_list));
		String number = MainActivity.number;
		if (number.length() > 2) {
			emrc_no_down.setText(number);
		}
	}

	private void findViewById() {
		TopLinearLayout = (LinearLayout) findViewById(R.id.TopLinearLayout);
		emrc_no_down = (TextView) findViewById(R.id.emrc_no_down);
		emrc_title = (TextView) findViewById(R.id.emrc_title);
		emrclist = (ListView) findViewById(R.id.emrclist);
		emrc_ok = (ImageView) findViewById(R.id.emrc_ok);
		emrclist.setOnItemClickListener(this);
		emrc_ok.setOnClickListener(this);
	}

	private void setCount() {
		int leve = MainActivity.leve_count;
		switch (leve) {
		case 0:
			TopLinearLayout.setBackgroundColor(Color.BLACK);
			emrc_title.setTextColor(Color.WHITE);
			Condition_list_string.add(getString( R.string.s_EMRC_00));
			Condition_list_string.add(getString( R.string.s_EMRC_01));
			Condition_list_string.add(getString( R.string.s_EMRC_02));
			Condition_list_string.add(getString( R.string.s_EMRC_03));
			Condition_list_string.add(getString( R.string.s_EMRC_04));
			break;
		case 1:
			TopLinearLayout.setBackgroundColor(Color.RED);
			emrc_title.setTextColor(Color.WHITE);
			Condition_list_string.add(getString( R.string.s_EMRC_10));
			Condition_list_string.add(getString( R.string.s_EMRC_11));
			Condition_list_string.add(getString( R.string.s_EMRC_12));
			Condition_list_string.add(getString( R.string.s_EMRC_13));
			Condition_list_string.add(getString( R.string.s_EMRC_14));
			Condition_list_string.add(getString( R.string.s_EMRC_15));
			Condition_list_string.add(getString( R.string.s_EMRC_16));
			Condition_list_string.add(getString( R.string.s_EMRC_17));
			Condition_list_string.add(getString( R.string.s_EMRC_18));
			Condition_list_string.add(getString( R.string.s_EMRC_19));
			Condition_list_string.add("");
			Condition_list_string.add("");
			break;
		case 2:
			TopLinearLayout.setBackgroundColor(Color.YELLOW);
			emrc_title.setTextColor(Color.BLACK);
			Condition_list_string.add(getString( R.string.s_EMRC_20));
			Condition_list_string.add(getString( R.string.s_EMRC_21));
			Condition_list_string.add(getString( R.string.s_EMRC_22));
			Condition_list_string.add(getString( R.string.s_EMRC_23));
			Condition_list_string.add(getString( R.string.s_EMRC_24));
			Condition_list_string.add(getString( R.string.s_EMRC_25));
			Condition_list_string.add(getString( R.string.s_EMRC_26));
			Condition_list_string.add("");
			Condition_list_string.add("");
			break;
		}
		for (int i = 0; i < Condition_list_string.size(); i++) {
			Condition_list_click.add(false);
		}
	}
}

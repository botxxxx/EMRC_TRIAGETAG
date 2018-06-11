package com.emrc_triagetag;

import com.emrc_triagetag.R.color;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LevelActivity extends Activity implements OnClickListener {
	private int count = 5;
	private Resources res;
	private ImageView ok;
	private LinearLayout leve_item_0, leve_item_1, leve_item_2, leve_item_3;
	private TextView leve_no_top, leve_no_down;
	private TextView leve_item_0_int, leve_item_1_int, leve_item_2_int, leve_item_3_int;
	private TextView leve_item_0_morgue, leve_item_1_immediate, leve_item_2_delayed, leve_item_3_minor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_3_leve);
		findViewById();
		reset();
		load();
	}

	protected void onStart() {
		super.onStart();
	}

	protected void onPause() {
		super.onPause();
	}

	protected void onResume() {
		super.onResume();
	}

	protected void onDestroy() {
		super.onDestroy();
	}

	private void save() {
		if (count != 5) {
			// String intArray[] = { "¦º¤`", "·¥¦MÀI", "¦MÀI", "»´¶Ë" };
			MainActivity.leve_count = count;
			// MainActivity.menu_item.set(4, "ÀË¶Ë¤ÀÃþ " + intArray[count] + ">");
			MainActivity.menu_upload(this);
		}
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	private void load() {
		if (MainActivity.leve_count != 5) {
			count = MainActivity.leve_count;
			Drawable drawable;
			ColorStateList color;
			switch (count) {
			case 0:
				drawable = res.getDrawable(R.color.black);
				leve_item_0.setBackground(drawable);
				color = res.getColorStateList(R.color.gray_li);
				leve_item_0_int.setTextColor(color);
				color = res.getColorStateList(R.color.white);
				leve_item_0_morgue.setTextColor(color);
				break;
			case 1:
				drawable = res.getDrawable(R.color.red);
				leve_item_1.setBackground(drawable);
				color = res.getColorStateList(R.color.gray_li);
				leve_item_1_int.setTextColor(color);
				color = res.getColorStateList(R.color.white);
				leve_item_1_immediate.setTextColor(color);
				break;
			case 2:
				drawable = res.getDrawable(R.color.gold);
				leve_item_2.setBackground(drawable);
				color = res.getColorStateList(R.color.gray_dr);
				leve_item_2_int.setTextColor(color);
				color = res.getColorStateList(R.color.black);
				leve_item_2_delayed.setTextColor(color);
				break;
			case 3:
				drawable = res.getDrawable(R.color.green);
				leve_item_3.setBackground(drawable);
				color = res.getColorStateList(R.color.gray_dr);
				leve_item_3_int.setTextColor(color);
				color = res.getColorStateList(R.color.black);
				leve_item_3_minor.setTextColor(color);
				break;
			}
		} else {
			Drawable drawable_b = res.getDrawable(R.color.black);
			leve_item_0.setBackground(drawable_b);
			ColorStateList color = res.getColorStateList(R.color.gray_li);
			leve_item_0_int.setTextColor(color);
			color = res.getColorStateList(R.color.white);
			leve_item_0_morgue.setTextColor(color);

			Drawable drawable_r = res.getDrawable(R.color.red);
			leve_item_1.setBackground(drawable_r);
			color = res.getColorStateList(R.color.gray_li);
			leve_item_1_int.setTextColor(color);
			color = res.getColorStateList(R.color.white);
			leve_item_1_immediate.setTextColor(color);

			Drawable drawable_y = res.getDrawable(R.color.gold);
			leve_item_2.setBackground(drawable_y);
			color = res.getColorStateList(R.color.gray_dr);
			leve_item_2_int.setTextColor(color);
			color = res.getColorStateList(R.color.black);
			leve_item_2_delayed.setTextColor(color);

			Drawable drawable_g = res.getDrawable(R.color.green);
			leve_item_3.setBackground(drawable_g);
			color = res.getColorStateList(R.color.gray_dr);
			leve_item_3_int.setTextColor(color);
			color = res.getColorStateList(R.color.black);
			leve_item_3_minor.setTextColor(color);
		}
		String number = MainActivity.number;
		if (number.length() > 2) {
			leve_no_top.setText(number);
			leve_no_down.setText(number);
		}
	}

	private void reset() {
		res = this.getResources();
		leve_item_0.setOnClickListener(this);
		leve_item_1.setOnClickListener(this);
		leve_item_2.setOnClickListener(this);
		leve_item_3.setOnClickListener(this);
		ok.setOnClickListener(this);
	}

	private void findViewById() {
		leve_item_0 = (LinearLayout) findViewById(R.id.leve_item_0);
		leve_item_1 = (LinearLayout) findViewById(R.id.leve_item_1);
		leve_item_2 = (LinearLayout) findViewById(R.id.leve_item_2);
		leve_item_3 = (LinearLayout) findViewById(R.id.leve_item_3);
		leve_item_0_int = (TextView) findViewById(R.id.leve_item_0_int);
		leve_item_1_int = (TextView) findViewById(R.id.leve_item_1_int);
		leve_item_2_int = (TextView) findViewById(R.id.leve_item_2_int);
		leve_item_3_int = (TextView) findViewById(R.id.leve_item_3_int);
		leve_item_0_morgue = (TextView) findViewById(R.id.leve_item_0_morgue);
		leve_item_1_immediate = (TextView) findViewById(R.id.leve_item_1_immediate);
		leve_item_2_delayed = (TextView) findViewById(R.id.leve_item_2_delayed);
		leve_item_3_minor = (TextView) findViewById(R.id.leve_item_3_minor);
		leve_no_top = (TextView) findViewById(R.id.leve_no_top);
		leve_no_down = (TextView) findViewById(R.id.leve_no_down);
		ok = (ImageView) findViewById(R.id.leve_check);
	}

	@SuppressWarnings("deprecation")
	private void defaule() {
		Drawable drawable = res.getDrawable(color.white);
		leve_item_0.setBackgroundDrawable(drawable);
		leve_item_1.setBackgroundDrawable(drawable);
		leve_item_2.setBackgroundDrawable(drawable);
		leve_item_3.setBackgroundDrawable(drawable);
		ColorStateList color = res.getColorStateList(R.color.gray_dr);
		leve_item_0_int.setTextColor(color);
		leve_item_1_int.setTextColor(color);
		leve_item_2_int.setTextColor(color);
		leve_item_3_int.setTextColor(color);
		color = res.getColorStateList(R.color.black);
		leve_item_0_morgue.setTextColor(color);
		leve_item_1_immediate.setTextColor(color);
		leve_item_2_delayed.setTextColor(color);
		leve_item_3_minor.setTextColor(color);

	}

	public void onClick(View v) {
		if (v == leve_item_0) {
			setColor(0);
		}
		if (v == leve_item_1) {
			setColor(1);
		}
		if (v == leve_item_2) {
			setColor(2);
		}
		if (v == leve_item_3) {
			setColor(3);
		}
		if (v == ok) {
			save();
			finish();
		}
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	private void setColor(int c) {
		if (c != count) {
			defaule();
			ColorStateList color;
			Drawable drawable;
			count = c;
			MainActivity.emrc_count = 0;
			switch (c) {
			case 0:
				drawable = res.getDrawable(R.color.black);
				leve_item_0.setBackground(drawable);
				color = res.getColorStateList(R.color.gray_li);
				leve_item_0_int.setTextColor(color);
				color = res.getColorStateList(R.color.white);
				leve_item_0_morgue.setTextColor(color);
				break;
			case 1:
				drawable = res.getDrawable(R.color.red);
				leve_item_1.setBackground(drawable);
				color = res.getColorStateList(R.color.gray_li);
				leve_item_1_int.setTextColor(color);
				color = res.getColorStateList(R.color.white);
				leve_item_1_immediate.setTextColor(color);
				break;
			case 2:
				drawable = res.getDrawable(R.color.gold);
				leve_item_2.setBackground(drawable);
				color = res.getColorStateList(R.color.gray_dr);
				leve_item_2_int.setTextColor(color);
				color = res.getColorStateList(R.color.black);
				leve_item_2_delayed.setTextColor(color);
				break;
			case 3:
				drawable = res.getDrawable(R.color.green);
				leve_item_3.setBackground(drawable);
				color = res.getColorStateList(R.color.gray_dr);
				leve_item_3_int.setTextColor(color);
				color = res.getColorStateList(R.color.black);
				leve_item_3_minor.setTextColor(color);
				break;
			}
			MainActivity.uploadview(this);
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			save();
			finish();
		}
		return false;
	}
}

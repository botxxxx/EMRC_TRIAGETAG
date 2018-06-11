package com.emrc_triagetag;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InjuredActivity extends Activity implements OnClickListener, OnTouchListener, OnPageChangeListener {
	private boolean F = false, B = false;
	private static List<View> view_list;
	private int downX = 0, downY = 0, pg = 0;
	private TextView inju_no_down;
	private RelativeLayout inju_log;
	private ViewPager pager;
	private List<String> title_list;
	private ImageView front_view, back_view, undo, ok;
	private ArrayList<Double> front_list = MainActivity.inju_front;
	private ArrayList<Double> back_list = MainActivity.inju_back;
	private DisplayMetrics metrics = new DisplayMetrics();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_1_inju);
		findViewById();
		addTab();
		addImage();
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

	private void Loading(boolean v) {
		if (v) {
			inju_log.setVisibility(View.VISIBLE);
		} else {
			inju_log.setVisibility(View.GONE);
		}
	}

	public void onClick(View v) {
		if (v == undo) {
			Loading(true);
			ArrayList<Double> tmp = new ArrayList<Double>();
			if (pg == 0) {
				for (int i = 0; i < front_list.size() - 2; i++) {
					tmp.add(front_list.get(i));
				}
				front_list.clear();
				for (int i = 0; i < tmp.size(); i++) {
					front_list.add(tmp.get(i));
				}
				print(front_list, front_view, 0);
				Loading(false);
				if (front_list.size() == 0) {
					undo.setVisibility(View.GONE);
					F = false;
				}
			} else {
				for (int i = 0; i < back_list.size() - 2; i++) {
					tmp.add(back_list.get(i));
				}
				back_list.clear();
				for (int i = 0; i < tmp.size(); i++) {
					back_list.add(tmp.get(i));
				}
				print(back_list, back_view, 1);
				Loading(false);
				if (back_list.size() == 0) {
					undo.setVisibility(View.GONE);
					B = false;
				}
			}
		}
		if (v == ok) {
			save();
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 開始位置
			downX = (int) event.getX();
			downY = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			// 移動位置
			break;
		case MotionEvent.ACTION_UP:
			// 離開位置
			int upX = (int) event.getX();
			int upY = (int) event.getY();
			// 點擊事件
			if (Math.abs(upX - downX) < 10 && Math.abs(upY - downY) < 10) {
				getPoint(upX, upY, (ImageView) v);
			}
			break;
		default:
			break;
		}
		return true;
	}

	private void getPoint(int x, int y, ImageView v) {

		if (v == front_view) {
			getView(x, y, front_view, front_list, true);
		}
		if (v == back_view) {
			getView(x, y, back_view, back_list, false);
		}
	}

	@SuppressLint("UseValueOf")
	private void getView(int x, int y, ImageView v, ArrayList<Double> list, boolean f) {
		boolean view = true;
		int mWidth = MainActivity.metrics.widthPixels; // 螢幕寬
		int mHeight = MainActivity.metrics.heightPixels; // 螢幕長
		for (int i = 0; i < list.size(); i += 2) {
			double getX = list.get(i) * mWidth;
			double getY = list.get(i + 1) * mHeight;
			if (Math.abs(getX - x) < 100 && Math.abs(getY - y) < 100) {
				startActivity(new Intent(InjuredActivity.this, PhotoActivity.class));
				view = false;
			}
		}
		if (view) {
			if (list.size() < 20) {
				list.add(getPercent(x, mWidth));
				list.add(getPercent(y, mHeight));
				if (f) {
					print(list, v, 0);
				} else {
					print(list, v, 1);
				}
			} else {
				Toast.makeText(this, getString(R.string.s_ts_45), Toast.LENGTH_SHORT).show();
			}
		}

		// Toast.makeText(this, "X:" + getPercent(x, mWidth) + " Y:" +
		// getPercent(y, mHeight), Toast.LENGTH_SHORT).show();
	}

	private double getPercent(Integer num, Integer totalPeople) {
		String percent;
		Double p3 = 0.0;
		if (totalPeople == 0) {
			p3 = 0.0;
		} else {
			p3 = num * 0.01 / totalPeople;
		}
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);// 控制保留小数点后几位，2：表示保留2位小数点
		percent = nf.format(p3);
		percent = percent.substring(0, percent.length() - 1);
		return Double.parseDouble(percent);
	}

	@SuppressWarnings("deprecation")
	private void print(ArrayList<Double> llf, ImageView v, int in) {
		int mDaySize = metrics.widthPixels / 100;// 字體大小
		int mCircle = mDaySize * 2;// 大圓
		Bitmap bitmap = Bitmap.createBitmap((int) v.getWidth(), (int) v.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		v.setBackgroundDrawable(new BitmapDrawable(bitmap));

		Paint mPaint = new Paint();
		for (int i = 0; i < llf.size(); i += 2) {
			mPaint.setColor(Color.parseColor("#ff4c4c")); // 大圓:紅
			mPaint.setStyle(Style.FILL);
			int mWidth = MainActivity.metrics.widthPixels; // 螢幕寬
			int mHeight = MainActivity.metrics.heightPixels; // 螢幕長
			int x = (int) (llf.get(i) * mWidth);
			int y = (int) (llf.get(i + 1) * mHeight);
			canvas.drawCircle(x, y, mCircle, mPaint);
		}
		if (llf.size() > 0) {
			switch (in) {
			case 0:
				undo.setVisibility(View.VISIBLE);
				F = true;
				break;
			case 1:
				undo.setVisibility(View.VISIBLE);
				B = true;
				break;
			case 2:
				B = true;
				break;
			}
		}
	}

	private void save() {
		String sv = "";
		ArrayList<String> tmp_string = new ArrayList<String>();
		if (front_list.size() > 0 || back_list.size() > 0) {
			if (front_list.size() > 0) {
				for (int i = 0; i < front_list.size(); i += 2) {
					double x = front_list.get(i), y = front_list.get(i + 1);
					sv = Tools.getInjury(this, x, y, 0);
					if (sv.length() > 1) {
						tmp_string.add(sv);
					}
				}
			}
			if (back_list.size() > 0) {
				for (int i = 0; i < back_list.size(); i += 2) {
					double x = back_list.get(i), y = back_list.get(i + 1);
					sv = Tools.getInjury(this, x, y, 1);
					if (sv.length() > 1) {
						tmp_string.add(sv);
					}
				}
			}
			if (tmp_string.size() > 0) {
				String t = "";
				boolean one = true;
				for (int i = 0; i < tmp_string.size(); i++) {
					if (tmp_string.get(i).length() > 0) {
						if (one) {
							one = false;
							// s = tmp_string.get(i);
							t = tmp_string.get(i);
						} else {
							// s += "\n" + tmp_string.get(i);
							t += tmp_string.get(i);
						}
					}
				}
				showDialog(t);
			} else {
				finish();
			}
		} else {
			MainActivity.menu_item.set(2, getString(R.string.s_Menu_3));
			MainActivity.menu_upload(this);
			finish();
		}
	}

	private void showDialog(String f) {
		final String s = f;
		MainActivity.menu_item.set(2, getString(R.string.s_Menu_3) + "\n<" + f + ">");
		MainActivity.menu_upload(this);
		new AlertDialog.Builder(this).setTitle(getString(R.string.s_ts_42))
				.setPositiveButton(getString(R.string.s_ts_12), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						finish();
					}
				}).setNegativeButton(getString(R.string.s_ts_13), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						if (MainActivity.info_data.size() > 0) {
							String name, age, other = "";
							name = MainActivity.info_data.get(0);
							age = MainActivity.info_data.get(1);
							other = MainActivity.info_data.get(2);
							MainActivity.info_data.clear();
							if (other.length() > 0) {
								other += "\n(" + getString(R.string.s_Menu_3) + ":" + s + ")";
							} else {
								other += "(" + getString(R.string.s_Menu_3) + ":" + s + ")";
							}
							MainActivity.info_data.add(name);
							MainActivity.info_data.add(age);
							MainActivity.info_data.add(other);
						} else {
							String other = "(" + getString(R.string.s_Menu_3) + ":" + s + ")";
							MainActivity.info_data.add("");
							MainActivity.info_data.add("");
							MainActivity.info_data.add(other);
						}
						finish();
					}
				}).show();
	}

	private void load() {
		String number = MainActivity.number;
		if (number.length() > 2) {
			inju_no_down.setText(number);
		}
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				print(front_list, front_view, 0);
				print(back_list, back_view, 2);
				Loading(false);
			}
		}, 500);
	}

	private void findViewById() {
		undo = (ImageView) findViewById(R.id.fs_rotate);
		ok = (ImageView) findViewById(R.id.fs_check);
		pager = (ViewPager) findViewById(R.id.inju_pager);
		inju_log = (RelativeLayout) findViewById(R.id.inju_log);
		inju_no_down = (TextView) findViewById(R.id.inju_no_down);
	}

	@SuppressLint("InflateParams")
	private void addTab() {
		@SuppressWarnings("static-access")
		LayoutInflater mInflater = getLayoutInflater().from(this);
		View v1 = mInflater.inflate(R.layout.item_1_inju_front_view, null);
		View v2 = mInflater.inflate(R.layout.item_1_inju_back_view, null);

		view_list = new ArrayList<View>();
		view_list.add(v1);
		view_list.add(v2);

		title_list = new ArrayList<String>();
		title_list.add(getString(R.string.s_ts_43)); // body front
		title_list.add(getString(R.string.s_ts_44)); // and back :)
		pager.setOnPageChangeListener(this);
		pager.setAdapter(new MyPagerAdapter(view_list));
		pager.setCurrentItem(0);

		getWindowManager().getDefaultDisplay().getMetrics(metrics);
	}

	private void addImage() {
		front_view = (ImageView) view_list.get(0).findViewById(R.id.front_canvas);
		back_view = (ImageView) view_list.get(1).findViewById(R.id.back_canvas);
		// OnClick-------
		undo.setOnClickListener(this);
		ok.setOnClickListener(this);
		// OnTouch-------
		back_view.setOnTouchListener(this);
		front_view.setOnTouchListener(this);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			save();
		}
		return false;
	}

	public void onPageScrollStateChanged(int arg0) {

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	public void onPageSelected(int arg0) {
		undo.setVisibility(View.GONE);
		switch (arg0) {
		case 0:
			pg = 0;
			if (F) {
				undo.setVisibility(View.VISIBLE);
			}
			break;
		case 1:
			pg = 1;
			if (B) {
				undo.setVisibility(View.VISIBLE);
			}
			break;
		}
	}
}

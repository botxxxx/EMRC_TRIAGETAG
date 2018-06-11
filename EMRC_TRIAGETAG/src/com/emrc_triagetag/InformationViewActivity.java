package com.emrc_triagetag;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView.ScaleType;

public class InformationViewActivity extends Activity {
	private ViewPager pager;
	private List<View> view_list;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_4_viewpager);
		findViewById();
		addTab();

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return false;
	}

	private void findViewById() {
		pager = (ViewPager) findViewById(R.id.photo_pager);
	}

	@SuppressLint("InflateParams")
	@SuppressWarnings("static-access")
	private void addTab() {
		// 設置頁面
		view_list = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater().from(this);
		View v = mInflater.inflate(R.layout.item_0_info_img_view, null);
		view_list.add(v);

		BitmapDrawable drawable = (BitmapDrawable) InformationActivity.info_photo.getDrawable();
		ZoomImage iv = (ZoomImage) view_list.get(0).findViewById(R.id.imgview);
		iv.setImageBitmap(drawable.getBitmap());
		iv.setScaleType(ScaleType.FIT_CENTER);
		// 建立配適器
		MyPagerAdapter myPagerAdapter = new MyPagerAdapter(view_list);
		pager.setAdapter(myPagerAdapter);
		pager.setCurrentItem(0); // 設置起始頁
	}
}
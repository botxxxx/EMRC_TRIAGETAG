package com.emrc_triagetag;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView.ScaleType;

public class PhotoViewActivity extends Activity {
	private ViewPager pager;
	private List<View> view_list;
	private ArrayList<ZoomImage> zoom_list = new ArrayList<ZoomImage>();

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
		View v2 = mInflater.inflate(R.layout.item_0_info_img_view, null);
		View v4 = mInflater.inflate(R.layout.item_0_info_img_view, null);
		View v8 = mInflater.inflate(R.layout.item_0_info_img_view, null);
		View v16 = mInflater.inflate(R.layout.item_0_info_img_view, null);
		View v32 = mInflater.inflate(R.layout.item_0_info_img_view, null);

		view_list.add(v);
		view_list.add(v2);
		view_list.add(v4);
		view_list.add(v8);
		view_list.add(v16);
		view_list.add(v32);

		if (PhotoActivity.i00) {
			BitmapDrawable drawable = (BitmapDrawable) PhotoActivity.item_0_img_0.getDrawable();
			Bitmap bitmap = drawable.getBitmap();
			ZoomImage iv = (ZoomImage) view_list.get(0).findViewById(R.id.imgview);
			iv.setImageBitmap(bitmap);
			iv.setScaleType(ScaleType.FIT_CENTER);
			zoom_list.add(iv);
		}
		if (PhotoActivity.i01) {
			BitmapDrawable drawable = (BitmapDrawable) PhotoActivity.item_0_img_1.getDrawable();
			Bitmap bitmap = drawable.getBitmap();
			ZoomImage iv = (ZoomImage) view_list.get(1).findViewById(R.id.imgview);
			iv.setImageBitmap(bitmap);
			iv.setScaleType(ScaleType.FIT_CENTER);
			zoom_list.add(iv);
		}
		if (PhotoActivity.i10) {
			BitmapDrawable drawable = (BitmapDrawable) PhotoActivity.item_1_img_0.getDrawable();
			Bitmap bitmap = drawable.getBitmap();
			ZoomImage iv = (ZoomImage) view_list.get(2).findViewById(R.id.imgview);
			iv.setImageBitmap(bitmap);
			iv.setScaleType(ScaleType.FIT_CENTER);
			zoom_list.add(iv);
		}
		if (PhotoActivity.i11) {
			BitmapDrawable drawable = (BitmapDrawable) PhotoActivity.item_1_img_1.getDrawable();
			Bitmap bitmap = drawable.getBitmap();
			ZoomImage iv = (ZoomImage) view_list.get(3).findViewById(R.id.imgview);
			iv.setImageBitmap(bitmap);
			iv.setScaleType(ScaleType.FIT_CENTER);
			zoom_list.add(iv);
		}
		if (PhotoActivity.i20) {
			BitmapDrawable drawable = (BitmapDrawable) PhotoActivity.item_2_img_0.getDrawable();
			Bitmap bitmap = drawable.getBitmap();
			ZoomImage iv = (ZoomImage) view_list.get(4).findViewById(R.id.imgview);
			iv.setImageBitmap(bitmap);
			iv.setScaleType(ScaleType.FIT_CENTER);
			zoom_list.add(iv);
		}
		if (PhotoActivity.i21) {
			BitmapDrawable drawable = (BitmapDrawable) PhotoActivity.item_2_img_1.getDrawable();
			Bitmap bitmap = drawable.getBitmap();
			ZoomImage iv = (ZoomImage) view_list.get(5).findViewById(R.id.imgview);
			iv.setImageBitmap(bitmap);
			iv.setScaleType(ScaleType.FIT_CENTER);
			zoom_list.add(iv);
		}
		// 建立配適器
		MyPagerAdapter myPagerAdapter = new MyPagerAdapter(view_list);
		pager.setAdapter(myPagerAdapter);
		pager.setCurrentItem(PhotoActivity.getSelect()); // 設置起始頁
	}
}
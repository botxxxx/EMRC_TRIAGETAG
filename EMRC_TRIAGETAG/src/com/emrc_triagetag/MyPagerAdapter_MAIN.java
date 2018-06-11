package com.emrc_triagetag;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter_MAIN extends PagerAdapter { // 继承适配器
	private ArrayList<View> aListView = new ArrayList<View>();

	MyPagerAdapter_MAIN(ArrayList<View> list) {
		for (int i = 0; i < list.size(); i++)
			aListView.add(list.get(i));
	}

	public int getCount() {
		// 返回当前页卡数量
		return aListView.size() - 1;
	}

	public boolean isViewFromObject(View view, Object object) {
		// View是否来自对象
		return view == object;
	}

	public Object instantiateItem(ViewGroup container, int position) {
		// 实例化一个页卡
		View view = aListView.get(position);
		container.addView(view);
		return view;
	}

	public void destroyItem(ViewGroup container, int position, Object object) { // 销毁页卡
		container.removeView((View) object);
	}

	public CharSequence getPageTitle(int position) {
		return null;
	}
}

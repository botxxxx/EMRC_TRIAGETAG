package com.emrc_triagetag;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter { // 继承适配器

	private List<View> viewList;

	// 实现构造方法
	public MyPagerAdapter(List<View> viewList) {
		this.viewList = viewList;
	}

	/*
	 * ViewPager正常一次加载三个 多余的摧毁
	 */
	

	public int getCount() {
		return viewList.size(); // 返回当前页卡数量
	}

	public boolean isViewFromObject(View view, Object object) {
		// View是否来自对象
		return view == object;
	}

	public Object instantiateItem(ViewGroup container, int position) {
		// 实例化一个页卡
		View view = viewList.get(position);
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

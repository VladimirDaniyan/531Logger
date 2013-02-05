package com.azazel11.android.fivethreeone.exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.azazel11.android.fivethreeone.R;
import com.azazel11.android.fivethreeone.exercise.week.WeekOne;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

public class BenchPress extends SherlockFragmentActivity {
	

	private static final String[] TAB_TITLES = new String[] { "Week 1", "Week 2", "Week 3", "Week 4", };

	    WeekOneFragmentAdapter mAdapter;
	    ViewPager mPager;
	    PageIndicator mIndicator;
	    
	    @Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			setContentView(R.layout.vpi_weeks_tabs);
	         
	        mAdapter = new WeekOneFragmentAdapter(getSupportFragmentManager());
	 
	        mPager = (ViewPager)findViewById(R.id.pager);
	        mPager.setAdapter(mAdapter);
	 
	        mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
	        mIndicator.setViewPager(mPager);
		}




		class WeekOneFragmentAdapter extends FragmentPagerAdapter {
			private int mCount = TAB_TITLES.length;

			public WeekOneFragmentAdapter(FragmentManager fm) {
				super(fm);
			}

			@Override
			public Fragment getItem(int position) {
				return new WeekOne();
			}

			@Override
			public int getCount() {
				return mCount;
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return TAB_TITLES[position];
			}
			
			

		}
}

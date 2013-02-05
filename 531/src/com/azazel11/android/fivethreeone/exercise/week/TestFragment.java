package com.azazel11.android.fivethreeone.exercise.week;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.azazel11.android.fivethreeone.R;

public class TestFragment extends SherlockFragment {
	private String mContent = "???";

	public static TestFragment newInstance(String text) {
		TestFragment fragment = new TestFragment();
		fragment.mContent = text;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test_activity, null);
		String text = getString(R.string.tab_page_num) + mContent;
		((TextView) view.findViewById(R.id.test_text)).setText(text);

		return view;
	}

}

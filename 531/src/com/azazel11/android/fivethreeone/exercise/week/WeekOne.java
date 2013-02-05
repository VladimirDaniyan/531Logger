package com.azazel11.android.fivethreeone.exercise.week;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.azazel11.android.fivethreeone.MaxCalculator;
import com.azazel11.android.fivethreeone.R;
import com.azazel11.android.fivethreeone.SetsDbAdapter;


public class WeekOne extends SherlockListFragment {
	
	static final int INSERT_ID = Menu.FIRST;

	private SetsDbAdapter mDbHelper;
	private Cursor mSetsCursor;

	private static final int ACTIVITY_CREATE = 0;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.sets_list, null);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);
		
		mDbHelper = new SetsDbAdapter(getSherlockActivity());
		mDbHelper.open();
		fillData();
	}

	@SuppressWarnings("deprecation")
	private void fillData() {
		// Get all of the sets form the database and create the item list
		mSetsCursor = mDbHelper.fetchAllNotes();
		getSherlockActivity().startManagingCursor(mSetsCursor);

		// Create an array to specify the fields we want to display in the list
		String[] from = new String[] { SetsDbAdapter.KEY_SET_ONE, SetsDbAdapter.KEY_SET_TWO, SetsDbAdapter.KEY_SET_THREE};

		// and an array of the fields we want to bind those fields to (in this
		// case just text1)
		int[] to = new int[] { R.id.tv_set_one, R.id.tv_set_two, R.id.tv_set_three };

		// Now create a simple cursor adapter and set it to display
		SimpleCursorAdapter sets = new SimpleCursorAdapter(
				getSherlockActivity(), android.R.layout.simple_list_item_1, mSetsCursor, from, to);
		setListAdapter(sets);

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuItem calcMaxItem = menu.add(Menu.NONE, INSERT_ID, 0, R.string.calc_one_max);
		calcMaxItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			createSet();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void createSet() {
		Intent i = new Intent(getSherlockActivity(), MaxCalculator.class);
		startActivityForResult(i, ACTIVITY_CREATE);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		Bundle extras = intent.getExtras();

		if (requestCode == ACTIVITY_CREATE) {
			String set_one = extras.getString(SetsDbAdapter.KEY_SET_ONE);
			String set_two = extras.getString(SetsDbAdapter.KEY_SET_TWO);
			String set_three = extras.getString(SetsDbAdapter.KEY_SET_THREE);
			mDbHelper.createSet(set_one, set_two, set_three);
			fillData();
		}

	}

}

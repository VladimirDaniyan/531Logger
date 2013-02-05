package com.azazel11.android.fivethreeone;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

public class MaxCalculator extends SherlockActivity {
	private EditText mWeight;
	private EditText mOneRM;
	private EditText mReps;
	private Long mRowId;
	private EditText m90;
	private EditText m85;
	private EditText m75;
	private EditText m65;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate_maxes);

		setTitle(R.string.title_activity_max_calculator);

		mWeight = (EditText) findViewById(R.id.etWeightLifted);
		mOneRM = (EditText) findViewById(R.id.etOneRepMax);
		m90 = (EditText) findViewById(R.id.et90);
		m85 = (EditText) findViewById(R.id.et85);
		m75 = (EditText) findViewById(R.id.et75);
		m65 = (EditText) findViewById(R.id.et65);
		mReps = (EditText) findViewById(R.id.etRepsPerformed);

		Button calculateMaxesButton = (Button) findViewById(R.id.bCalculateMaxes);
	

		mRowId = null;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String set_one = extras.getString(SetsDbAdapter.KEY_SET_ONE);
			String set_two = extras.getString(SetsDbAdapter.KEY_SET_TWO);
			String set_three = extras.getString(SetsDbAdapter.KEY_SET_THREE);
			mRowId = extras.getLong(SetsDbAdapter.KEY_ROWID);

			if (set_one != null) {
				m65.setText(set_one);
			}
			if (set_two != null) {
				m75.setText(set_two);
			}
			if (set_three != null) {
				m85.setText(set_two);
			}

		}
		
	

		calculateMaxesButton.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View view) {

				double weightVar = Double.parseDouble(mWeight.getText()
						.toString());
				double repsVar = Double.parseDouble(mReps.getText().toString());

				// calculate sets

				double oneRMVal = weightVar * repsVar * .0333 + weightVar;
				double setOneVal = .65 * .9 * oneRMVal;
				double setTwoVal = .75 * .9 * oneRMVal;
				double setThreeVal = .85 * .9 * oneRMVal;

				DecimalFormat df = new DecimalFormat("@@@");
				df.setRoundingMode(RoundingMode.DOWN);

				mOneRM.setText(df.format(oneRMVal));
				m65.setText(df.format(setOneVal));
				m75.setText(df.format(setTwoVal));
				m85.setText(df.format(setThreeVal));

				Bundle bundle = new Bundle();

				bundle.putString(SetsDbAdapter.KEY_SET_ONE, m65
						.getText().toString());
				bundle.putString(SetsDbAdapter.KEY_SET_TWO, m75
						.getText().toString());
				bundle.putString(SetsDbAdapter.KEY_SET_THREE, m85
						.getText().toString());
				if (mRowId != null) {
					bundle.putDouble(SetsDbAdapter.KEY_ROWID, mRowId);
				}

				Intent mIntent = new Intent();
				mIntent.putExtras(bundle);
				setResult(RESULT_OK, mIntent);
			}

		});

	}
}

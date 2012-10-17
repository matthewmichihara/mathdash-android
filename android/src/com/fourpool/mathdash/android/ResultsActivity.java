package com.fourpool.mathdash.android;

import java.text.MessageFormat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);

		Intent intent = getIntent();
		int correct = intent.getIntExtra("correct", 0);
		int total = intent.getIntExtra("total", 0);

		int score = correct * 100;

		SharedPreferences sharedPreferences = getSharedPreferences("prefs", 0);
		int currentHighScore = sharedPreferences.getInt("current_highscore", 0);
		if (score > currentHighScore) {
			SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
			sharedPreferencesEditor.putInt("current_highscore", score);
			sharedPreferencesEditor.commit();
		}

		TextView scoreTextView = (TextView) findViewById(R.id.results_score);
		scoreTextView.setText(Integer.toString(score));

		TextView correctTextView = (TextView) findViewById(R.id.results_correct);
		correctTextView.setText(Integer.toString(correct));

		TextView totalTextView = (TextView) findViewById(R.id.results_total);
		totalTextView.setText(Integer.toString(total));

		// Prevent NaN.
		double d = 0;
		if (total != 0) {
			d = ((double) correct) / ((double) total);
		}
		String accuracy = MessageFormat.format("{0,number,#.##%}", d);
		TextView accuracyTextView = (TextView) findViewById(R.id.results_accuracy);
		accuracyTextView.setText(accuracy);
	}

}

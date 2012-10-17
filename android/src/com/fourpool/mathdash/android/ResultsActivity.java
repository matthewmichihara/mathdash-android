package com.fourpool.mathdash.android;

import java.text.MessageFormat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;

public class ResultsActivity extends SherlockActivity {

	private int correct;
	private int total;
	private int score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);

		Intent intent = getIntent();
		correct = intent.getIntExtra("correct", 0);
		total = intent.getIntExtra("total", 0);
		score = correct * 100;

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.results, menu);

		MenuItem menuItem = menu.findItem(R.id.menu_share);

		ShareActionProvider mShareActionProvider = (ShareActionProvider) menuItem.getActionProvider();

		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		shareIntent.setType("text/plain");

		shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text, score));
		mShareActionProvider.setShareIntent(shareIntent);
		return true;
	}
}

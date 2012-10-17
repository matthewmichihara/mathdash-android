package com.fourpool.mathdash.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

public class MathDashActivity extends SherlockActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SharedPreferences sharedPreferences = getSharedPreferences("prefs", 0);
		int highScore = sharedPreferences.getInt("current_highscore", 0);
		TextView highScoreTextView = (TextView) findViewById(R.id.main_high_score);
		highScoreTextView.setText(Integer.toString(highScore));

		Button playButton = (Button) findViewById(R.id.playButton);
		playButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MathDashActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();

		SharedPreferences sharedPreferences = getSharedPreferences("prefs", 0);
		int currentHighScore = sharedPreferences.getInt("current_highscore", 0);
		TextView highScoreTextView = (TextView) findViewById(R.id.main_high_score);
		highScoreTextView.setText(Integer.toString(currentHighScore));
	}
}
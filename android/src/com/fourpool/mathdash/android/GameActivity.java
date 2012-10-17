package com.fourpool.mathdash.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.fourpool.mathdash.android.model.RandomEquation;

public class GameActivity extends SherlockActivity {

	private int correct = 0;
	private int total = 0;

	private final int gameLengthInMillis = 60000; // 1 minute

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		// Set countdown
		new CountDownTimer(gameLengthInMillis, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				TextView timeRemainingTextView = (TextView) findViewById(R.id.gameTimeRemaining);
				timeRemainingTextView.setText(Long.toString(millisUntilFinished / 1000));
			}

			@Override
			public void onFinish() {
				Intent intent = new Intent(GameActivity.this, ResultsActivity.class);
				intent.putExtra("correct", correct);
				intent.putExtra("total", total);
				startActivity(intent);
				finish();
			}
		}.start();

		refreshEquation();
	}

	private void refreshEquation() {
		final RandomEquation randomEquation = new RandomEquation();

		TextView answerTextView = (TextView) findViewById(R.id.gameEquation);
		answerTextView.setText(randomEquation.equation);

		View.OnClickListener clickListener = new View.OnClickListener() {

			public void onClick(View v) {
				total += 1;
				if (((Button) v).getText() == Integer.toString(randomEquation.answer)) {
					correct += 1;
				}

				TextView solvedTextView = (TextView) findViewById(R.id.gameSolved);
				solvedTextView.setText(correct + "/" + total);
				refreshEquation();
			}
		};

		Button gameButton1 = (Button) findViewById(R.id.gameButton1);
		gameButton1.setText(Integer.toString(randomEquation.answer1));
		gameButton1.setOnClickListener(clickListener);

		Button gameButton2 = (Button) findViewById(R.id.gameButton2);
		gameButton2.setText(Integer.toString(randomEquation.answer2));
		gameButton2.setOnClickListener(clickListener);

		Button gameButton3 = (Button) findViewById(R.id.gameButton3);
		gameButton3.setText(Integer.toString(randomEquation.answer3));
		gameButton3.setOnClickListener(clickListener);

		Button gameButton4 = (Button) findViewById(R.id.gameButton4);
		gameButton4.setText(Integer.toString(randomEquation.answer4));
		gameButton4.setOnClickListener(clickListener);
	}
}

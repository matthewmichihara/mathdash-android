package com.fourpool;

import java.text.MessageFormat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		
		Bundle extras = getIntent().getExtras();
		
		int correct = 0;
		int total = 0;
		if (extras != null) {
			correct = extras.getInt("correct");
			total = extras.getInt("total");
		}
		
		TextView correctTextView = (TextView)findViewById(R.id.results_correct);
		correctTextView.setText(Integer.toString(correct));
		
		TextView totalTextView = (TextView)findViewById(R.id.results_total);
		totalTextView.setText(Integer.toString(total));
		
		
		String accuracy = MessageFormat.format("{0,number,#.##%}", ((double)correct)/((double)total));
		TextView accuracyTextView = (TextView)findViewById(R.id.results_accuracy);
		accuracyTextView.setText(accuracy);
	}
}

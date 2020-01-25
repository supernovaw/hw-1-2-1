package com.example.homework121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
	private static HashMap<String, String> subscriptions = new HashMap<>();

	private EditText emailInput, nameInput;
	private Button subscribeButton, clearButton;
	private TextView resultTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();

		subscribeButton.setOnClickListener(v -> {
			String mail = String.valueOf(emailInput.getText());
			String name = String.valueOf(nameInput.getText());
			subscribe(mail, name);
		});
		clearButton.setOnClickListener(v -> {
			emailInput.setText(null);
			nameInput.setText(null);
			resultTextView.setText(null);
		});
	}

	private void initViews() {
		emailInput = findViewById(R.id.emailInput);
		nameInput = findViewById(R.id.nameInput);
		subscribeButton = findViewById(R.id.subscribeButton);
		clearButton = findViewById(R.id.clearButton);
		resultTextView = findViewById(R.id.resultTextView);
	}

	private void subscribe(String mail, String name) {
		String output;
		if (mail.trim().isEmpty())
			output = getString(R.string.type_email);
		else if (!mail.contains("@"))
			output = getString(R.string.invalid_email);
		else if (name.trim().isEmpty())
			output = getString(R.string.type_name);
		else if (subscriptions.containsKey(mail))
			output = getString(R.string.already_subscribed);
		else {
			subscriptions.put(mail, name);
			output = String.format(getString(R.string.subscribed_successfully), mail);
		}
		resultTextView.setText(output);
	}
}

package com.example.homework121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
	private static HashMap<String, String> subscriptions = new HashMap<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.subscribeButton).setOnClickListener(v -> {
			String mail = String.valueOf(((EditText) findViewById(R.id.emailInput)).getText());
			String name = String.valueOf(((EditText) findViewById(R.id.nameInput)).getText());
			subscribe(mail, name);
		});
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
			((EditText) findViewById(R.id.emailInput)).setText(null);
			((EditText) findViewById(R.id.nameInput)).setText(null);
		}
		Toast.makeText(this, output, Toast.LENGTH_LONG).show();
	}
}

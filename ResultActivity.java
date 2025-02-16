package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.resultText);
        shareButton = findViewById(R.id.shareButton);

        String userName = getIntent().getStringExtra("userName");
        int score = getIntent().getIntExtra("score", 0);

        resultText.setText(userName + ", Your Score: " + score + "/2");

        shareButton.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Quiz Score");
            shareIntent.putExtra(Intent.EXTRA_TEXT, userName + " scored: " + score + "/2 in the quiz.");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }
}

package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup optionsGroup;
    private Button nextButton, prevButton;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String userName;

    private String[] questions = {"What year did FIFA start?", "Who discovered gravity?"};
    private String[][] options = {{"1920", "1930", "1940", "1950"}, {"Newton", "Einstein", "Tesla", "Edison"}};
    private int[] correctAnswers = {1, 0}; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        
        userName = getIntent().getStringExtra("userName");

        loadQuestion();

        nextButton.setOnClickListener(v -> {
            int selectedId = optionsGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedAnswer = findViewById(selectedId);
                int answerIndex = optionsGroup.indexOfChild(selectedAnswer);
                if (answerIndex == correctAnswers[currentQuestionIndex]) {
                    score++;
                }
            }

            if (currentQuestionIndex < questions.length - 1) {
                currentQuestionIndex++;
                loadQuestion();
            } else {
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });

        prevButton.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                loadQuestion();
            }
        });
    }

    private void loadQuestion() {
        questionText.setText(questions[currentQuestionIndex]);
        optionsGroup.clearCheck();
    }
}

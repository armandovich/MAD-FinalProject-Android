package com.example.finalproyectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView scoreText;
    TextView resultText;
    ImageButton retryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.testResult);
        scoreText = findViewById(R.id.testScore);
        retryButton = findViewById(R.id.retryButton);

        int counter = getIntent().getIntExtra("correctAnswers",0);

        String result = "";

        if (counter == 3) {
            result = "Good job!";
        } else if (counter == 4) {
            result= "Excellent work!";
        } else if (counter == 5) {
            result = "You are a genius!";
        } else {
            result = "Please try again!";
        }

        resultText.setText(result);
        scoreText.setText(counter + "/5");
    }

    public void retryTest(View View){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

}
package com.example.finalproyectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    // Create variables for GUI elements
    TextView scoreText;
    TextView resultText;
    ImageButton retryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get GUI elements that will change
        resultText = findViewById(R.id.testResult);
        scoreText = findViewById(R.id.testScore);
        retryButton = findViewById(R.id.retryButton);

        // Get extra content from intent, so we can get
        // number of correct answers
        int counter = getIntent().getIntExtra("correctAnswers",0);

        // Create a defaul string for the result
        String result = "";

        // Check result message
        if (counter == 3) {
            result = "Good job!";
        } else if (counter == 4) {
            result= "Excellent work!";
        } else if (counter == 5) {
            result = "You are a genius!";
        } else {
            result = "Please try again!";
        }

        // Display result message
        resultText.setText(result);
        // Display how many correct answers
        scoreText.setText(counter + "/5");
    }

    // Return to quiz activity
    public void retryTest(View View){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

}
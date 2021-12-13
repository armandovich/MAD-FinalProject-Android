package com.example.finalproyectapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    // Private globar variables to handle user interaction
    private int currentQuestion ;
    private ArrayList<Integer> answers = new ArrayList<>();
    private Test test;

    // Store GUI elements that will change on runtime
    ImageView questionImage;
    TextView questionText;
    ImageButton nextBtn;
    ImageButton prevBtn;
    ImageButton submitBtn;
    RadioGroup rg;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;

    // Create a Result Launcher to reset test incase user
    // want to retry from result activity
    ActivityResultLauncher<Intent> quizAvtivityResult = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            // Check if a result was send after finishing an activity
            if (result.getResultCode() == Activity.RESULT_OK) {
                // reset current question
                currentQuestion = 0;

                // reset answers
                for (int i = 0; i < 5; i++) {
                    answers.set(i,-1);
                }

                // reset GUI elements
                test.ResetTest();
                rg.clearCheck();
                nextBtn.setClickable(true);
                prevBtn.setClickable(false);
                submitBtn.setClickable(false);
                nextBtn.setImageResource(R.drawable.next);
                prevBtn.setImageResource(R.drawable.prev_hide);
                submitBtn.setImageResource(R.drawable.submit_hide);
                UpdateQuestionPanel();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        // Look for all the GUI elements that will change on runtime
        questionImage = findViewById(R.id.questionImage);
        questionText = findViewById(R.id.questionText);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);
        submitBtn = findViewById(R.id.submitBtn);

        rg = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);

        prevBtn.setClickable(false);
        submitBtn.setClickable(false);
        prevBtn.setImageResource(R.drawable.prev_hide);
        submitBtn.setImageResource(R.drawable.submit_hide);

        // Init global variables
        currentQuestion = 0;

        for (int i = 0; i < 5; i++) {
            answers.add(-1);
        }

        test = new Test();

        UpdateQuestionPanel();
    }

    // Method to check radio button selection
    public void checkSelection(View view) {
        switch (view.getId()){
            case R.id.radioButton1:
                answers.set(currentQuestion,0);
                break;
            case R.id.radioButton2:
                answers.set(currentQuestion,1);
                break;
            case R.id.radioButton3:
                answers.set(currentQuestion,2);
                break;
            case R.id.radioButton4:
                answers.set(currentQuestion,3);
                break;
        }

        // Temp boolean to active submit button
        boolean canSubmit = true;

        // Check if all question has been answer
        for(int n: answers) {
            if (n == -1) {
                canSubmit = false;
            }
        }

        // Check if submit can be done and updates its image
        if (canSubmit) {
            submitBtn.setImageResource(R.drawable.submit);
        } else {
            submitBtn.setImageResource(R.drawable.submit_hide);
        }

        // Toggle if submit can be or not clikeable
        submitBtn.setClickable(canSubmit);
    }

    // Method to check how GUI buttons needs to change
    public void CheckButtonStatus() {
        // Check if next button should be active
        if (currentQuestion > 3) {
            nextBtn.setImageResource(R.drawable.next_hide);
        } else {
            nextBtn.setImageResource(R.drawable.next);
        }

        // Check if next button prev be active
        if (currentQuestion < 1) {
            prevBtn.setImageResource(R.drawable.prev_hide);
        } else {
            prevBtn.setImageResource(R.drawable.prev);
        }

        // Set clickable statis fro next and prev button
        nextBtn.setClickable(currentQuestion < 4);
        prevBtn.setClickable(currentQuestion > 0);

        // Update question layout area
        UpdateQuestionPanel();

        // clean radio group checked element
        rg.clearCheck();

        // Check if curren question has been answer,
        // so we can activate that checkbox
        int tempAnswer = answers.get(currentQuestion);

        // Check index of the checkbox that needs to be activated
        if(tempAnswer == 0) {
            rb1.setChecked(true);
        } else if(tempAnswer == 1) {
            rb2.setChecked(true);
        } else if(tempAnswer == 2) {
            rb3.setChecked(true);
        } else if(tempAnswer == 3) {
            rb4.setChecked(true);
        }
    }

    // Method to update questioon layout area
    public void UpdateQuestionPanel() {
        // Get question
        Question tempQ = test.GetQuestion(currentQuestion);
        // Set question image
        questionImage.setImageResource(tempQ.getImage());
        // Set question description
        questionText.setText(tempQ.getDescription());
        // Set question answer options
        rb1.setText(tempQ.getOptions().get(0));
        rb2.setText(tempQ.getOptions().get(1));
        rb3.setText(tempQ.getOptions().get(2));
        rb4.setText(tempQ.getOptions().get(3));
    }

    // Go to next question
    public void nextQuestion(View view) {
        currentQuestion += 1;
        CheckButtonStatus();
    }

    // Go to previous question
    public void prevQuestion(View view) {
        currentQuestion -= 1;
        CheckButtonStatus();
    }

    // Submit test
    public void submitTest(View view) {
        int correctAnswers = 0;

        // Check how many correct answer
        for (int i = 0; i < 5; i++) {
            if (test.CheckAnswer(i,answers.get(i))) {
                correctAnswers += 1;
            }
        }

        // Create intent and open result activity
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        // Send number of correct answers
        intent.putExtra("correctAnswers", correctAnswers);
        quizAvtivityResult.launch(intent);
    }
}
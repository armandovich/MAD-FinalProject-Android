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

    private int currentQuestion ;
    private ArrayList<Integer> answers = new ArrayList<>();
    private Test test;

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

    ActivityResultLauncher<Intent> quizAvtivityResult = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                currentQuestion = 0;

                for (int i = 0; i < 5; i++) {
                    answers.set(i,-1);
                }

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

        currentQuestion = 0;

        for (int i = 0; i < 5; i++) {
            answers.add(-1);
        }

        test = new Test();

        UpdateQuestionPanel();
    }

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

        boolean canSubmit = true;

        for(int n: answers) {
            if (n == -1) {
                canSubmit = false;
            }
        }

        if (canSubmit) {
            submitBtn.setImageResource(R.drawable.submit);
        } else {
            submitBtn.setImageResource(R.drawable.submit_hide);
        }

        submitBtn.setClickable(canSubmit);
    }

    public void CheckButtonStatus() {
        if (currentQuestion > 3) {
            nextBtn.setImageResource(R.drawable.next_hide);
        } else {
            nextBtn.setImageResource(R.drawable.next);
        }

        if (currentQuestion < 1) {
            prevBtn.setImageResource(R.drawable.prev_hide);
        } else {
            prevBtn.setImageResource(R.drawable.prev);
        }

        nextBtn.setClickable(currentQuestion < 4);
        prevBtn.setClickable(currentQuestion > 0);

        UpdateQuestionPanel();

        rg.clearCheck();

        int tempAnswer = answers.get(currentQuestion);

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

    public void UpdateQuestionPanel() {
        Question tempQ = test.GetQuestion(currentQuestion);
        questionImage.setImageResource(tempQ.getImage());
        questionText.setText(tempQ.getDescription());
        rb1.setText(tempQ.getOptions().get(0));
        rb2.setText(tempQ.getOptions().get(1));
        rb3.setText(tempQ.getOptions().get(2));
        rb4.setText(tempQ.getOptions().get(3));
    }

    public void nextQuestion(View view) {
        currentQuestion += 1;
        CheckButtonStatus();
    }

    public void prevQuestion(View view) {
        currentQuestion -= 1;
        CheckButtonStatus();
    }

    public void submitTest(View view) {
        int correctAnswers = 0;

        for (int i = 0; i < 5; i++) {
            if (test.CheckAnswer(i,answers.get(i))) {
                correctAnswers += 1;
            }
        }

        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("correctAnswers", correctAnswers);
        quizAvtivityResult.launch(intent);
    }
}
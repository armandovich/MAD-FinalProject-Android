package com.example.finalproyectapp;

import androidx.appcompat.app.AppCompatActivity;

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
    RadioGroup radioGroup;
    RadioButton selectedRadio;
    ImageButton nextBtn;
    ImageButton prevBtn;
    ImageButton submitBtn;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionImage = findViewById(R.id.questionImage);
        questionText = findViewById(R.id.questionText);
        radioGroup = findViewById(R.id.radioGroup);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);
        submitBtn = findViewById(R.id.submitBtn);

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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1:
                        break;
                    case R.id.radioButton2:
                        break;
                    case R.id.radioButton3:
                        break;
                    case R.id.radioButton4:
                        break;
                }
            }
        });
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

        prevBtn.setClickable(currentQuestion < 4);
        prevBtn.setClickable(currentQuestion > 0);

        UpdateQuestionPanel();

        int tempAnswer = answers.get(currentQuestion);
        rb1.setChecked(tempAnswer == 0);
        rb1.setChecked(tempAnswer == 1);
        rb2.setChecked(tempAnswer == 2);
        rb3.setChecked(tempAnswer == 3);
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

    }

    public void prevQuestion(View view) {

    }

    public void submitTest(View view) {

    }

    public void setNextButton(View view){
        //Next Question
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        startActivity(intent);
    }
}
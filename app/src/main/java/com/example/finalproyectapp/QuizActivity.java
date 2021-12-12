package com.example.finalproyectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    ImageView questionImage;
    TextView questionText;
    RadioGroup radioGroup;
    Button nextBtn;
    Button prevBtn;
    Button submitBtn;

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

    public void setNextButton(View view){
        //Next Question
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        startActivity(intent);
    }
}
package com.example.finalproyectapp;

import java.util.ArrayList;

public class Question {
    private int image;
    private String description;
    private ArrayList<String> options;
    private int answer;

    public Question(int image, String description, ArrayList<String> options, int answer) {
        this.image = image;
        this.description = description;
        this.options = options;
        this.answer = answer;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}

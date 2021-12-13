package com.example.finalproyectapp;

import java.util.ArrayList;

public class Test {
    // Create question list
    private ArrayList<Question> questions = new ArrayList<>();
    // Create selected answer list
    private ArrayList<Integer> selections = new ArrayList<>();

    // Constructor
    public Test() {
        CreateQuestions();
        PicFivekQuestions();
    }

    // Getter and Setters
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Integer> getSelections() {
        return selections;
    }

    public void setSelections(ArrayList<Integer> selections) {
        this.selections = selections;
    }

    // Create questions
    private void CreateQuestions() {
        ArrayList<String> answerQ1 = new ArrayList<>();
        answerQ1.add("Stephen Fry");
        answerQ1.add("Steve Jobs");
        answerQ1.add("Stephen Hawking");
        answerQ1.add("Bill Gates");
        questions.add(new Question(R.drawable.one, "Who founded Apple Computer?", answerQ1, 1));

        ArrayList<String> answerQ2 = new ArrayList<>();
        answerQ2.add("NASA");
        answerQ2.add("ENIAC");
        answerQ2.add("UNIVAC");
        answerQ2.add("SAGE");
        questions.add(new Question(R.drawable.two, "Which of these is not an early computer?", answerQ2, 0));

        ArrayList<String> answerQ3 = new ArrayList<>();
        answerQ3.add("Lada");
        answerQ3.add("Apple");
        answerQ3.add("Toshiba");
        answerQ3.add("HP");
        questions.add(new Question(R.drawable.three, "Which of these is not a computer brand?", answerQ3, 0));

        ArrayList<String> answerQ4 = new ArrayList<>();
        answerQ4.add("World While Web");
        answerQ4.add("World Wide Workstation");
        answerQ4.add("World Wide Web");
        answerQ4.add("Windows Wide Web");
        questions.add(new Question(R.drawable.four, "What does WWW stand for?", answerQ4, 2));

        ArrayList<String> answerQ5 = new ArrayList<>();
        answerQ5.add("1979");
        answerQ5.add("1980");
        answerQ5.add("1981");
        answerQ5.add("1982");
        questions.add(new Question(R.drawable.five,"When was the compact disc public release?", answerQ5, 3));

        ArrayList<String> answerQ6 = new ArrayList<>();
        answerQ6.add("Microsoft");
        answerQ6.add("Apple");
        answerQ6.add("IBM");
        answerQ6.add("Oracle");
        questions.add(new Question(R.drawable.six, "Who is the maker of the iPhone?", answerQ6, 1));

        ArrayList<String> answerQ7 = new ArrayList<>();
        answerQ7.add("Telephone wire");
        answerQ7.add("Pipeline");
        answerQ7.add("Human hair");
        answerQ7.add("None");
        questions.add(new Question(R.drawable.seven,"What does fiber optic cable resemble, in terms of size?", answerQ7, 2));

        ArrayList<String> answerQ8 = new ArrayList<>();
        answerQ8.add("iMAX");
        answerQ8.add("iPhone");
        answerQ8.add("iMac");
        answerQ8.add("iPad");
        questions.add(new Question(R.drawable.eight, "Which of these products is not made by the Apple Corporation?", answerQ8, 0));

        ArrayList<String> answerQ9 = new ArrayList<>();
        answerQ9.add("Charles Babbage");
        answerQ9.add("iPhone");
        answerQ9.add("iMac");
        answerQ9.add("iPad");
        questions.add(new Question(R.drawable.nine, "Who invented the first computer?", answerQ9, 0));

        ArrayList<String> answerQ10 = new ArrayList<>();
        answerQ10.add("UNIVAC");
        answerQ10.add("MOSFET");
        answerQ10.add("ABACO");
        answerQ10.add("ENIAC");
        questions.add(new Question(R.drawable.ten, "what was the name of the first computer?", answerQ10, 3));

        ArrayList<String> answerQ11 = new ArrayList<>();
        answerQ11.add("Python");
        answerQ11.add("Java");
        answerQ11.add("Swift");
        answerQ11.add("HTML");
        questions.add(new Question(R.drawable.eleven, "which one is not a programming language?", answerQ11, 3));

        ArrayList<String> answerQ12 = new ArrayList<>();
        answerQ12.add("Swift");
        answerQ12.add("C#");
        answerQ12.add("Java");
        answerQ12.add("Kotlin");
        questions.add(new Question(R.drawable.twelve, "What language are iOS apps coded in??", answerQ12, 0));
    }

    // Pick 5 random questions
    public void PicFivekQuestions() {
        // Pick a random index
        int number = (int) (Math.random() * (questions.size() - 0) + 0);

        // If index is not duplicated save it
        if(!selections.contains(number)) {
            selections.add(number);
        }

        // Check if we alredt pick 5 questions
        if (selections.size() < 5) {
            // Recursive function to pick next question
            PicFivekQuestions();
        }
    }

    // Reset question list
    public void ResetTest() {
        selections.clear();
        PicFivekQuestions();
    }

    // Get question by index
    public Question GetQuestion(int number) {
        return questions.get(selections.get(number));
    }

    // Check if answer is correct
    public boolean CheckAnswer(int number, int answer) {
        return questions.get(selections.get(number)).getAnswer() == answer;
    }
}

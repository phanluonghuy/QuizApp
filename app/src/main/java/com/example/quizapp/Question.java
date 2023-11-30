package com.example.quizapp;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> choices;
    private String answer;
//    private URI uri;
    public Question() {

    }

    public Question(String question, List<String> choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", choices=" + choices +
                ", answer='" + answer + '\'' +
                '}';
    }
}

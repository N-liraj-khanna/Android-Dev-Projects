package com.example.yoaquiz.model;

import androidx.annotation.NonNull;

public class Question {
    private String question;
    private boolean isTrue;
    private int flag=0;

    public Question() {}

    public Question(String question, boolean isTrue) {
        this.question = question;
        this.isTrue = isTrue;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", isTrue=" + isTrue +
                '}';
    }
}

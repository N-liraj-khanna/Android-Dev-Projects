package com.example.yoaquiz.data;

import com.example.yoaquiz.model.Question;

import java.util.List;

public interface QuestionRetrieveAsyncResponse {
    void processFinished(List<Question> allQuestions);
}

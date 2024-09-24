package com.springauth.SpringAuth.Question;

import java.util.List;

import lombok.Data;

@Data
public class QuestionRequest {
    private List<String> questions;
    private Integer projectId;

//    public List<String> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(List<String> questions) {
//        this.questions = questions;
//    }
}

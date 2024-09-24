package com.springauth.SpringAuth.Question;

import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springauth.SpringAuth.user.Role;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    
    @PostMapping("/post")
    public Question saveQuestion(@RequestBody Question question) {
        //TODO: process POST request
        
        return questionService.create(question);
    }
    
    @PostMapping("/bulk")
    public List<Question> createQuestions(@RequestBody QuestionRequest request) {
        return questionService.saveQuestions(request.getProjectId(), request.getQuestions());
    }
    
    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/by-project")
    public List<Question> getQuestionsByProjectId(@RequestParam Integer projectId) {
        return questionService.getQuestionsByProjectId(projectId);
    }
    
    @PostMapping("/submit-answers")
    public ResponseEntity<String> submitAnswers(@RequestBody AnswersRequest request) {
        Integer projectId = request.getProjectId();
        Map<Integer, String> answers = request.getAnswers();

        boolean success = questionService.saveAnswers(projectId, answers);

        if (success) {
            return ResponseEntity.ok("Answers submitted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit answers.");
        }
    }
    
}


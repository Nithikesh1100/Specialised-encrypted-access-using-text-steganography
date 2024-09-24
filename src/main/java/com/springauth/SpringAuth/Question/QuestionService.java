package com.springauth.SpringAuth.Question;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springauth.SpringAuth.Encryption.EncryptionService;
import com.springauth.SpringAuth.user.Role;

@Service
public class QuestionService {
	
	private final QuestionRepository questionsRepository;
    private final EncryptionService encryptionService;

    @Autowired
    public QuestionService(QuestionRepository questionsRepository, EncryptionService encryptionService) {
        this.questionsRepository = questionsRepository;
        this.encryptionService = encryptionService;
    }
	
	
	public List<Question> saveQuestions(Integer projectId, List<String> questions) {
        List<Question> questionEntities = questions.stream()
            .map(text -> {
                Question question = new Question();
                question.setProjectId(projectId);
                question.setQuestion(encryptionService.encrypt(text));
                return question;
            })
            .collect(Collectors.toList());
        return questionsRepository.saveAll(questionEntities);
    }


	public Question create(Question question) {
		// TODO Auto-generated method stub
		question.setQuestion(encryptionService.encrypt(question.getQuestion()));
		return questionsRepository.save(question);
		
	}
	
	public List<Question> getQuestionsByProjectId(Integer projectId) {
        List<Question> questions = questionsRepository.findByProjectId(projectId);
        return questions.stream()
            .map(question -> {
                question.setQuestion(encryptionService.decrypt(question.getQuestion()));
                return question;
            })
            .collect(Collectors.toList());
    }

    public List<Question> getAllQuestions() {
        return questionsRepository.findAll();
    }
    
    public boolean saveAnswers(Integer projectId, Map<Integer, String> answers) {
        try {
            for (Map.Entry<Integer, String> entry : answers.entrySet()) {
                Integer questionId = entry.getKey();
                String answer = entry.getValue();

                Optional<Question> optionalQuestion = questionsRepository.findById(questionId);
                if (optionalQuestion.isPresent()) {
                    Question question = optionalQuestion.get();
                    question.setAnswer(answer);
                    questionsRepository.save(question);
                } else {
                    throw new RuntimeException("Question not found with ID: " + questionId);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}

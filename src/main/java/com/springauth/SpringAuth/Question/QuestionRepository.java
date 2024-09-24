package com.springauth.SpringAuth.Question;


import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByProjectId(Integer projectId);
}

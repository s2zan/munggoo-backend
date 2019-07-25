package com.mashup.munggoo.quiz.repository;

import com.mashup.munggoo.quiz.quizGenerator.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Boolean existsQuizzesByFileId(Long fileId);
    void deleteQuizzesByFileId(Long fileId);
    List<Quiz> findByFileIdOrderByStartIndex(Long fileId);
}

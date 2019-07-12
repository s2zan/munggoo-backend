package com.mashup.munggoo.quiz;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Boolean existsQuizzesByFileId(Long fileId);
    void deleteQuizzesByFileId(Long fileId);
    List<Quiz> findByFileId(Long fileId);
}

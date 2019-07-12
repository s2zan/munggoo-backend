package com.mashup.munggoo.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_id", nullable = false)
    private Long fileId;

    @Column(name = "start_index", nullable = false)
    private Long startIndex;

    @Column(name = "end_index", nullable = false)
    private Long endIndex;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    public Quiz(QuizDto quizDto) {
        this.fileId = quizDto.getFileId();
        this.startIndex = quizDto.getStartIndex();
        this.endIndex = quizDto.getEndIndex();
        this.content = quizDto.getContent();
    }
    public static Quiz from(QuizDto quizDto) {
        return new Quiz(quizDto);
    }
}

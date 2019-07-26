package com.mashup.munggoo.quiz.domain;

import com.mashup.munggoo.quiz.quizGenerator.Word;
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

    public Quiz(Word word) {
        this.fileId = word.getFileId();
        this.startIndex = word.getStartIndex();
        this.endIndex = word.getEndIndex();
        this.content = word.getContent();
    }
    public static Quiz from(Word word) {
        return new Quiz(word);
    }
}

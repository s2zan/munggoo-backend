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

    public Quiz(Long fileId, Long startIndex, Long endIndex, String content) {
        this.fileId = fileId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.content = content;
    }
}

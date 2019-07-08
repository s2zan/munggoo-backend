package com.mashup.munggoo.highlight;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Highlight {
    public enum HightlightType { WORD, SENTENCE }

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HightlightType type;

    @Column(name = "is_important", nullable = false)
    private Boolean isImportant;

    public Highlight(Long fileId, Long startIndex, Long endIndex, String content, HightlightType type, Boolean isImportant) {
        this.fileId = fileId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.content = content;
        this.type = type;
        this.isImportant = isImportant;
    }
}

package com.mashup.munggoo.highlight;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Highlight {
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
    private HighlightType type;

    @Column(name = "is_important", nullable = false)
    private Boolean isImportant;

    public Highlight(Long fileId, ReqHighlightDto reqHighlightDto) {
        this.fileId = fileId;
        this.startIndex = reqHighlightDto.getStartIndex();
        this.endIndex = reqHighlightDto.getEndIndex();
        this.content = reqHighlightDto.getContent();
        this.type = reqHighlightDto.stringToEnum(reqHighlightDto.getContent());
        this.isImportant = reqHighlightDto.getIsImportant();
    }

    public static Highlight from(Long fileId, ReqHighlightDto reqHighlightDto) {
        return new Highlight(fileId, reqHighlightDto);
    }
}

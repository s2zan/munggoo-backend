package com.mashup.munggoo.highlight;

import com.mashup.munggoo.file.File;
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

    @Column(nullable = false)
    private int type;

    @Column(nullable = false)
    private int priority;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;

    public Highlight(HighlightDto highlightDto) {
        this.fileId = highlightDto.getFileId();
        this.startIndex = highlightDto.getStartIndex();
        this.endIndex = highlightDto.getEndIndex();
        this.content = highlightDto.getContent();
        this.type = highlightDto.getType();
        this.priority = highlightDto.getPriority();
    }

    public static Highlight from(HighlightDto highlightDto) {
        return new Highlight(highlightDto);
    }
}

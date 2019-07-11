package com.mashup.munggoo.file;

import lombok.Getter;

@Getter
public class ResFileDto {
    private Long id;
    private String name;

    public ResFileDto(File file) {
        id = file.getId();
        name = file.getName();
    }
}

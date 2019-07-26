package com.mashup.munggoo.file;

import lombok.Getter;

import java.util.List;

@Getter
public class ResFilesDto {
    private List<ResFileDto> files;

    public ResFilesDto(List<ResFileDto> files) {
        this.files = files;
    }
}

package com.mashup.munggoo.version;

import lombok.Getter;

@Getter
public class ResVersionDto {
    private String version;
    private String link;

    public ResVersionDto(Version version) {
        this.version = version.getVersion();
        this.link = version.getLink();
    }
}

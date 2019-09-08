package com.mashup.munggoo.version;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@Entity
public class Version {
    @Id
    private String version;

    @Column(nullable = false)
    private String link;
}

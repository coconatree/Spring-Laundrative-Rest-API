package com.laundrative_v1.app.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.laundrative_v1.app.entity.VersionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class VersionDao
{
    private Long id;
    private String explanation;
    private Date date;

    public VersionDao(VersionEntity entity)
    {
        this.id = entity.getId();
        this.explanation = entity.getExplanation();
        this.date = entity.getDate();
    }
}

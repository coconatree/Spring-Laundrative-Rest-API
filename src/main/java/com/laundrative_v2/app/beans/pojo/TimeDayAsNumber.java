package com.laundrative_v2.app.beans.pojo;

import lombok.*;

import java.sql.Time;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TimeDayAsNumber
{
    private Time time;
    private Integer day;
}

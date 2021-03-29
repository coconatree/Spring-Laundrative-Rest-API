package com.laundrative_v2.app.beans.json.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import static com.laundrative_v2.app.configuration.UtilityConfig.DATE_FORMAT_1;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHoursJson
{
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String startingDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String  endingDate;

    public WorkingHoursJson(Date startingDate, Date endingDate)
    {
        this.startingDate = DATE_FORMAT_1.format(startingDate);
        this.endingDate = DATE_FORMAT_1.format(endingDate);
    }
}

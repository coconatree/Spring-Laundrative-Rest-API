package com.laundrative_v2.app.beans.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

import static com.laundrative_v2.app.configuration.UtilityConfig.DATE_FORMAT_1;

@Getter
@ToString
@NoArgsConstructor
public class WorkingHoursJson
{
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date startingDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date  endingDate;

    public WorkingHoursJson(Date startingDate, Date endingDate)
    {
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
}

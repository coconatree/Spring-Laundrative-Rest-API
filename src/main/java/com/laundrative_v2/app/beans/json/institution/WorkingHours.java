package com.laundrative_v2.app.beans.json.institution;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@Getter
@ToString
@NoArgsConstructor
public class WorkingHours
{
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date startingTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date endingTime;

    public WorkingHours(Date startingDate, Date endingDate)
    {
        this.startingTime = startingDate;
        this.endingTime   = endingDate;
    }
}

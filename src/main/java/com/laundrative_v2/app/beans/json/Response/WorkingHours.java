package com.laundrative_v2.app.beans.json.Response;

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
    private Date startingDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date  endingDate;

    public WorkingHours(Date startingDate, Date endingDate)
    {
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
}

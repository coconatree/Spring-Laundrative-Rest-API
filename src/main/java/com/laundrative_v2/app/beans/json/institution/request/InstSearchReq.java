package com.laundrative_v2.app.beans.json.institution.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class InstSearchReq
{
    private Long neighborhoodId;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date date;
    private Long [] categories;
}

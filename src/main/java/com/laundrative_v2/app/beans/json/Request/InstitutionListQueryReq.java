package com.laundrative_v2.app.beans.json.Request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionListQueryReq
{
    private Long neighborhoodId;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private Date clientDate;
    private Long [] listOfCategories;
}

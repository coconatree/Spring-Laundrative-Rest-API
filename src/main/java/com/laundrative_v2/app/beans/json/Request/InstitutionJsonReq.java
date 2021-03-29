package com.laundrative_v2.app.beans.json.Request;


import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionJsonReq
{
    private Long neighborhoodId;
    private Date date;
    List<Long> listOfCategories;
}

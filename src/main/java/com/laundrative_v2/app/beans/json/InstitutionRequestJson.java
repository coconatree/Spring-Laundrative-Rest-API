package com.laundrative_v2.app.beans.json;


import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionRequestJson
{
    private Long neighborhoodId;
    private Date date;
    ArrayList<Long> listOfCategories;
}

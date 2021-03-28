package com.laundrative_v2.app.beans.json;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionResponseJson
{
    private Long neighborhoodId;
    private String neighborhoodName;
    private Date startingHour;
    private Date closingHour;
    private String institutionName;
    private Long institutionId;
    private BigDecimal minimumOrderPrice;
    private BigDecimal maximumServicePrice;
    private BigDecimal freeServicePrice;
    private Boolean isFavorite;
}

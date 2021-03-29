package com.laundrative_v2.app.beans.json.Response;

import com.laundrative_v2.app.beans.db.Institution.InstitutionWorkingDb;
import com.laundrative_v2.app.beans.pojo.WorkingHoursJson;
import com.laundrative_v2.app.util.Utility;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InstitutionListQueryRes
{
    private Long neighborhoodId;
    private String neighborhoodName;
    private WorkingHoursJson workingHours;
    private String institutionName;
    private Long institutionId;
    private BigDecimal minimumOrderPrice;
    private BigDecimal maximumServicePrice;
    private BigDecimal freeServicePrice;
    private Boolean isFavorite;

    public void initWorkingHours(Date clientDate, InstitutionWorkingDb working)
    {
        workingHours = (Utility.createWorkingHoursJson(
                    clientDate,
                    working.getDay(),
                    working.getStartingTime(),
                    working.getEndingTime()
                    ));
    }
}

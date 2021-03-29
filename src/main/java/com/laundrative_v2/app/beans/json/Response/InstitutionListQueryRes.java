package com.laundrative_v2.app.beans.json.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.laundrative_v2.app.beans.db.Institution.InstitutionWorkingDb;
import com.laundrative_v2.app.beans.pojo.WorkingHoursJson;
import com.laundrative_v2.app.util.Utility;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
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

    public InstitutionListQueryRes()
    {
        this.workingHours = null;
    }

    public void init(Date date)
    {
        this.workingHours = new WorkingHoursJson(date, date);
    }
}

package com.laundrative_v2.app.beans.json.institution.response;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionDb;
import com.laundrative_v2.app.beans.db.institutionDb.InstitutionServiceDb;
import com.laundrative_v2.app.beans.db.institutionDb.InstitutionWorkingDb;
import com.laundrative_v2.app.beans.json.institution.NeighborhoodInfo;
import com.laundrative_v2.app.beans.json.institution.WorkingHours;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InstListQueryRes
{
    private Long neighborhoodId;
    private String neighborhoodName;
    private WorkingHours workingHours;
    private String institutionName;
    private Long institutionId;
    private BigDecimal minimumOrderPrice;
    private BigDecimal maximumServicePrice;
    private BigDecimal freeServicePrice;
    private Boolean isFavorite;

    public static InstListQueryRes from(InstitutionDb db, NeighborhoodInfo neighborhoodInfo, Date date)
    {
        InstListQueryRes response = new InstListQueryRes();
        InstitutionServiceDb service = null;
        InstitutionWorkingDb working = null;

        response.setNeighborhoodId(neighborhoodInfo.getNeighborhoodId());
        response.setNeighborhoodName(neighborhoodInfo.getNeighborhoodName());

        for (InstitutionServiceDb element : db.getInstitutionServiceList())
        {
            if(element.getNeighborhoodId() == neighborhoodInfo.getNeighborhoodId())
            {
                service = element;
                break;
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        for (InstitutionWorkingDb element : db.getInstitutionWorkingList())
        {
            if(element.getDay() == calendar.get(Calendar.DAY_OF_WEEK))
            {
                working = element;
                break;
            }
        }

        response.setWorkingHours(new WorkingHours(working.getStartingTime(), working.getEndingTime()));

        response.setInstitutionName(db.getInstitutionName());
        response.setInstitutionId(db.getId());
        response.setMinimumOrderPrice(service.getMinOrderAmount());
        response.setMaximumServicePrice(new BigDecimal(1000));
        response.setMaximumServicePrice(service.getMinServiceAmount());
        response.setIsFavorite(false);

        return response;
    }
}

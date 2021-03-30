package com.laundrative_v2.app.beans.json.Response;

import com.laundrative_v2.app.beans.db.Institution.InstitutionDb;
import com.laundrative_v2.app.beans.db.Institution.InstitutionServiceDb;
import lombok.*;

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

    public static InstListQueryRes from(InstitutionDb db, NeighborhoodInfo neighborhoodInfo)
    {
        InstListQueryRes response = new InstListQueryRes();
        InstitutionServiceDb service = null;

        response.setNeighborhoodId(neighborhoodInfo.getNeighborhoodId());
        response.setNeighborhoodName(neighborhoodInfo.getNeighborhoodName());

        for (InstitutionServiceDb element : db.getInstitutionServiceList())
        {
            if(element.neighborhoodDb.getId() == neighborhoodInfo.getNeighborhoodId())
            {
                service = element;
                break;
            }
        }

        response.setInstitutionName(db.getInstitutionName());
        response.setInstitutionId(db.getId());
        response.setMinimumOrderPrice(service.getMinOrderAmount());
        response.setMaximumServicePrice(new BigDecimal(1000));
        response.setMaximumServicePrice(service.getMinServiceAmount());
        response.setIsFavorite(false);

        //response.init(db);

        return response;
    }
}

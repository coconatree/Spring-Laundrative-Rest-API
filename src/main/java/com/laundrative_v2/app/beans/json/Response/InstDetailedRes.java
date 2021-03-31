package com.laundrative_v2.app.beans.json.Response;


import com.laundrative_v2.app.beans.db.Institution.InstitutionDb;
import com.laundrative_v2.app.beans.db.Institution.InstitutionServiceDb;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InstDetailedRes
{
    private Long neighborhoodId;
    private String neighborhoodName;
    private List<WorkingHours2> workingHoursList;
    private String institutionName;
    private Long institutionId;
    private BigDecimal minOrderPrice;
    private BigDecimal maxOrderPrice;
    private BigDecimal freeService;
    private boolean isFavorite;

    public static InstDetailedRes from(InstitutionDb db, NeighborhoodInfo neighborhoodInfo)
    {
        InstDetailedRes response = new InstDetailedRes();

        response.setNeighborhoodId(neighborhoodInfo.getNeighborhoodId());
        response.setNeighborhoodName(neighborhoodInfo.getNeighborhoodName());

        // Working hours list

        response.setWorkingHoursList(db.getInstitutionWorkingList().stream().map(e -> WorkingHours2.from(e)).collect(Collectors.toList()));

        response.setInstitutionName(db.getInstitutionName());
        response.setInstitutionId(db.getId());

        InstitutionServiceDb service = null;

        for (InstitutionServiceDb element : db.getInstitutionServiceList())
        {
            if(element.getNeighborhoodId() == neighborhoodInfo.getNeighborhoodId())
            {
                service = element;
                break;
            }
        }

        response.setMinOrderPrice(service.getMinOrderAmount());
        response.setMaxOrderPrice(new BigDecimal(100.00)); // ??? doesn't exist in the table

        //TODO
        //-Correct the business logic

        response.setFreeService(new BigDecimal(0.0)); // For know

        // JWT is needed
        response.setFavorite(false);

        return response;
    }
}

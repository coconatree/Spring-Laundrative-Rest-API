package com.laundrative_v2.app.beans.json.institution;

import com.laundrative_v2.app.beans.db.addressDb.NeighborhoodDb;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class NeighborhoodInfo
{
    private Long neighborhoodId;
    private String neighborhoodName;

    public static NeighborhoodInfo from(NeighborhoodDb db)
    {
        NeighborhoodInfo neighborhoodInfo = new NeighborhoodInfo();

        neighborhoodInfo.setNeighborhoodId(db.getId());
        neighborhoodInfo.setNeighborhoodName(db.getNeighborhoodName());

        return neighborhoodInfo;
    }
}

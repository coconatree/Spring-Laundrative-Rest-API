package com.laundrative_v2.app.beans.json.address.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressListRes
{
    private String districtName;
    private Long   neighborhoodId;
    private String neighborhoodName;

    public static AddressListRes from(String districtName, Long neighborhoodId, String neighborhoodName)
    {
        AddressListRes addressListRes = new AddressListRes();

        addressListRes.setDistrictName(districtName);
        addressListRes.setNeighborhoodId(neighborhoodId);
        addressListRes.setNeighborhoodName(neighborhoodName);

        return addressListRes;
    }
}

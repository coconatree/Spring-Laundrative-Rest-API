package com.laundrative_v2.app.beans.json.address.response;

import com.laundrative_v2.app.beans.db.addressDb.ProvinceDb;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressProvinceRes
{
    private Long provinceId;
    private String provinceName;

    public static AddressProvinceRes from(ProvinceDb db)
    {
        AddressProvinceRes addressProvinceRes =  new AddressProvinceRes();

        addressProvinceRes.setProvinceId(db.getId());
        addressProvinceRes.setProvinceName(db.getProvinceName());

        return addressProvinceRes;
    }
}

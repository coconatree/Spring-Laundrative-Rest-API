package com.laundrative_v2.app.beans.json.institution.response;

import com.laundrative_v2.app.beans.db.KindDb;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InstInfoQueryRes
{
    private Long categoryId;
    private Long kindId;
    private String kindName;
    private String kindImage;
    private Long type;
    private BigDecimal price;

    public static InstInfoQueryRes from(KindDb db)
    {
        //TODO
        // Change the default values

        InstInfoQueryRes object = new InstInfoQueryRes();

        object.setCategoryId(db.getCategory());
        object.setKindId(db.getId());
        object.setKindName(db.getName());
        object.setKindImage(db.getImage());
        object.setType(null);
        object.setPrice(new BigDecimal(10.00)); // Will be changed

        return object;
    }
}

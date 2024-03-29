package com.laundrative_v2.app.beans.json.institution.response;

import com.laundrative_v2.app.beans.db.KindDb;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class KindPrice
{
    private Long categoryId;
    private Long kindId;
    private String kindName;
    private String kindImage;
    private Long type;
    private BigDecimal price;

    public static KindPrice from(KindDb db)
    {
        //TODO
        // Change the default values

        KindPrice kindPrice = new KindPrice();

        kindPrice.setCategoryId(db.getCategory());
        kindPrice.setKindId(db.getId());
        kindPrice.setKindName(db.getName());
        kindPrice.setKindImage(db.getImage());
        kindPrice.setType(null);
        kindPrice.setPrice(new BigDecimal(10.00)); // Will be changed

        return kindPrice;
    }
}

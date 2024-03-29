package com.laundrative_v2.app.beans.json.product.response;

import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.utility.Utility;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ProductRes
{
    private Long categoryId;
    private String name;
    private String image;

    public static ProductRes from(KindDb db)
    {
        ProductRes response = null;

        System.out.println("DB : " + db);

        if(db != null)
        {
            response = new ProductRes();

            response.setCategoryId(db.getCategory());
            response.setImage(Utility.getInstance().imageToBase64(db.getImage()));
            response.setName(db.getName());
        }

        return response;
    }

    public static boolean isNull(ProductRes product)
    {
        return product == null;
    }
}

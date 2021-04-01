package com.laundrative_v2.app.beans.json.institution.response;

import com.laundrative_v2.app.beans.db.KindDb;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InstInfoQueryRes
{
    private Long categoryId;
    private List<KindPrice> kindPriceList;

    public void add(KindPrice object)
    {
        if(kindPriceList == null)
        {
            kindPriceList = new ArrayList<>();
        }
        kindPriceList.add(object);
    }

    public static InstInfoQueryRes from(Long categoryId, List<KindDb> db)
    {
        InstInfoQueryRes kindType = new InstInfoQueryRes();
        kindType.setCategoryId(categoryId);
        kindType.setKindPriceList(db.stream().map(e -> KindPrice.from(e)).collect(Collectors.toList()));
        return kindType;
    }
}

package com.laundrative_v2.app.beans.pojo;

import lombok.*;

import java.util.ArrayList;

@Getter
@ToString
@NoArgsConstructor
public class KindAndTypeJson
{
    private Long categoryId;
    private ArrayList<KindPriceJson> kindPriceJsonList;

    private int size = 0;

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    private void init()
    {
        this.kindPriceJsonList = new ArrayList<>();
    }

    public void add(KindPriceJson obj)
    {
        if(size == 0)
            this.init();

        this.kindPriceJsonList.add(obj);

        size++;
    }

    public int getSize()
    {
        return this.size;
    }
}

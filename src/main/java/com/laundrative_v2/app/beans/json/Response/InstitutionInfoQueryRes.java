package com.laundrative_v2.app.beans.json.Response;

import com.laundrative_v2.app.beans.pojo.KindAndTypeJson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InstitutionInfoQueryRes
{
    private ArrayList<KindAndTypeJson> list;
    private int size = 0;

    private void init()
    {
        this.list = new ArrayList<>();
    }

    public void add(KindAndTypeJson obj)
    {
        if(size == 0)
            this.init();

        this.list.add(obj);

        size++;
    }

    public int getSize()
    {
        return this.size;
    }
}

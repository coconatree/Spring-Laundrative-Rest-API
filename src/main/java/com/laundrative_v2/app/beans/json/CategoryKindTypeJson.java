package com.laundrative_v2.app.beans.json;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryKindTypeJson
{
    private Long category;
    private Long kind;
    private Long type;
}

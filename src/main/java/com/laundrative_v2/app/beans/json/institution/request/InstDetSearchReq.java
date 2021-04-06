package com.laundrative_v2.app.beans.json.institution.request;

import com.laundrative_v2.app.beans.json.institution.blocks.CategoryKindBlock;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class InstDetSearchReq
{
    private Long idN;
    private Date dateR;
    private Date dateD;
    private CategoryKindBlock [] blocks;
    private Boolean fs;
}

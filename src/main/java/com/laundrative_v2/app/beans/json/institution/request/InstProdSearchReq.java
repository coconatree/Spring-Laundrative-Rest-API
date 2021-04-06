package com.laundrative_v2.app.beans.json.institution.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstProdSearchReq
{
    private Long neighborhoodId;
    private String param;
}

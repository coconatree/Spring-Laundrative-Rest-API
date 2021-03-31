package com.laundrative_v2.app.beans.json.Request;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InstDetailedReq
{
    private Long neighborhoodId;
    private Date receivingDate;
    private  Date deliveryDate;
    private  CategoryKind [] array;
    private  Boolean freeService;
}

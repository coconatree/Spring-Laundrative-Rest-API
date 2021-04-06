package com.laundrative_v2.app.beans.json.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteFormReq
{
    private String reason;
    private String explanation;
}

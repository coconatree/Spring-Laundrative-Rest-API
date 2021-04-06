package com.laundrative_v2.app.beans.json.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDelReq
{
    private String title;
    private String reason;
}

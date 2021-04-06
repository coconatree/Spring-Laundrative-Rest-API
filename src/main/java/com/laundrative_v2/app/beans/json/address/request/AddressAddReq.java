package com.laundrative_v2.app.beans.json.address.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressAddReq
{
    private Long  neighborhoodId;
    private String title;
    private String address;
    private String receiverTelephone;
    private int isReceivingAddress;
    private int isDeliveryAddress;
}

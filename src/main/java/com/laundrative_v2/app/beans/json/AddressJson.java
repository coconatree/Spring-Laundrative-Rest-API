package com.laundrative_v2.app.beans.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressJson
{
    private Long neighborhoodId;
    private String title;
    private String receiverAddress;
    private String receiverTelephone;
}

package com.laundrative_v2.app.beans.json.customer;


import com.laundrative_v2.app.beans.db.customerDb.CustomerAddressDb;
import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddressObject
{
    private Long id;
    private Long  neighborhoodId;
    private String title;
    private String address;
    private String receiverTelephone;
    private int isReceivingAddress;
    private int isDeliveryAddress;

    public static AddressObject from(CustomerAddressDb db)
    {
        AddressObject addressPostReq = new AddressObject();

        addressPostReq.setId(db.getId());
        addressPostReq.setNeighborhoodId(db.getNeighborhoodId());
        addressPostReq.setAddress(db.getAddress());

        addressPostReq.setIsReceivingAddress(db.getReceiving());
        addressPostReq.setIsDeliveryAddress(db.getHandingOver());

        //addressPostReq.setTitle(db.get);
        //addressPostReq.setReceiverTelephone(db.ge);

        return addressPostReq;
    }
}

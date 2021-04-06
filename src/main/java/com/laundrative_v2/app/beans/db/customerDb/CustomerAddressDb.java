package com.laundrative_v2.app.beans.db.customerDb;

import com.laundrative_v2.app.beans.json.address.request.AddressAddReq;
import com.laundrative_v2.app.beans.json.customer.AddressObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "musteri_adres")
@Getter
@Setter
@ToString
public class CustomerAddressDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "musteri_id")
    private Long customerId;
    @Column(name = "mahalle_id")
    private Long neighborhoodId;
    @Column(name = "adres")
    private String address;
    @Column(name = "teslim_alma")
    private Integer receiving;
    @Column(name = "teslim_etme")
    private Integer handingOver;
    @Column(name = "aktif")
    private Integer active;

    public CustomerAddressDb(){}

    public void delete()
    {
        this.active = 0;
    }

    public static CustomerAddressDb from(Long customerId, AddressAddReq request)
    {
        CustomerAddressDb customerAddressDb = new CustomerAddressDb();

        customerAddressDb.setCustomerId(customerId);
        customerAddressDb.setNeighborhoodId(request.getNeighborhoodId());
        customerAddressDb.setAddress(request.getAddress());
        customerAddressDb.setReceiving(request.getIsReceivingAddress());
        customerAddressDb.setHandingOver(request.getIsDeliveryAddress());
        customerAddressDb.setActive(1);

        return customerAddressDb;
    }

    public static CustomerAddressDb updateFrom(CustomerAddressDb original, AddressAddReq request)
    {
        original.setNeighborhoodId(request.getNeighborhoodId());
        original.setAddress(request.getAddress());
        original.setReceiving(request.getIsReceivingAddress());
        original.setHandingOver(request.getIsDeliveryAddress());

        return original;
    }
}

package com.laundrative_v2.app.beans.db.customerDb;

import com.laundrative_v2.app.beans.json.address.AddressObject;
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
    private int receiving;
    @Column(name = "teslim_etme")
    private int handingOver;

    public CustomerAddressDb(){}

    public static CustomerAddressDb from(Long customerId, AddressObject request)
    {
        CustomerAddressDb customerAddressDb = new CustomerAddressDb();

        customerAddressDb.setCustomerId(customerId);
        customerAddressDb.setNeighborhoodId(request.getNeighborhoodId());
        customerAddressDb.setAddress(request.getAddress());
        customerAddressDb.setReceiving(request.getIsReceivingAddress());
        customerAddressDb.setHandingOver(request.getIsDeliveryAddress());

        return customerAddressDb;
    }

    public static CustomerAddressDb updateFrom(Long customerId, AddressObject request)
    {
        CustomerAddressDb customerAddressDb = new CustomerAddressDb();

        customerAddressDb.setCustomerId(customerId);
        customerAddressDb.setId(request.getId());
        customerAddressDb.setNeighborhoodId(request.getNeighborhoodId());
        customerAddressDb.setAddress(request.getAddress());
        customerAddressDb.setReceiving(request.getIsReceivingAddress());
        customerAddressDb.setHandingOver(request.getIsDeliveryAddress());

        return customerAddressDb;
    }
}

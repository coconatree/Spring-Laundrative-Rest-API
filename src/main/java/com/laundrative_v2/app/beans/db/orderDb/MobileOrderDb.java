package com.laundrative_v2.app.beans.db.orderDb;

import com.laundrative_v2.app.beans.json.order.request.OrderPostReq;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mobile_siparis")
@Getter
@Setter
@ToString
public class MobileOrderDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tarih")
    private Date date;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "notlar")
    private String notes;
    @Column(name = "siparis_id")
    private Long orderId;

    public static MobileOrderDb from(Long orderId, OrderPostReq request)
    {
        MobileOrderDb mobileOrderDb = new MobileOrderDb();

        mobileOrderDb.setOrderId(orderId);
        mobileOrderDb.setDate(null);
        mobileOrderDb.setName("NEEDS LOGGING SYSTEM");
        mobileOrderDb.setAddress(null); // Which address
        mobileOrderDb.setPhone("NEEDS LOGGING SYSTEM");
        mobileOrderDb.setEmail("NEEDS LOGGING SYSTEM");
        mobileOrderDb.setNotes(request.getNote());

        return mobileOrderDb;
    }
}

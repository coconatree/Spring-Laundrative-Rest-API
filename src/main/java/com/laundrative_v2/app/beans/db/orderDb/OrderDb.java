package com.laundrative_v2.app.beans.db.orderDb;

import com.laundrative_v2.app.beans.json.order.request.OrderPostReq;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "siparis")
@Getter
@Setter
@ToString
public class OrderDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "musteri_id")
    private Long customerId;
    @Column(name = "siparis_tarihi")
    private Date orderDate;
    @Column(name = "siparis_yeri")
    private Integer orderLocation;
    @Column(name = "tutar")
    private BigDecimal total;
    @Column(name = "nakit")
    private BigDecimal cash;
    @Column(name = "pos")
    private BigDecimal pos;
    @Column(name = "notlar")
    private String notes;
    @Column(name = "indirim")
    private BigDecimal discount;
    @Column(name = "indirim_tipi")
    private Integer discountType;
    @Column(name = "teslim_alma_tarihi")
    private Date receivingDate;
    @Column(name = "teslim_alma_adres")
    private String receivingAddress;
    @Column(name = "teslim_etme_tarihi")
    private Date deliveryDate;
    @Column(name = "teslim_etme_adres")
    private String deliveryAddress;
    @Column(name = "teslimat_status")
    private int deliveryStatus;
    @Column(name = "odeme_status")
    private int paymentStatus;

    public OrderDb(){}

    static public OrderDb from(OrderPostReq request, Long customerId)
    {
        OrderDb orderDb = new OrderDb();

        // UNTIL WE START USING JWT

        orderDb.setAccountId(1L); // DONT KNOW
        orderDb.setCustomerId(customerId);

        orderDb.setOrderDate(null);
        orderDb.setTotal(request.getTotal());
        orderDb.setCash(null);
        orderDb.setPos(null);
        orderDb.setNotes(request.getNote());
        orderDb.setDiscount(request.getDiscountPercentage());
        orderDb.setDiscountType(null);

        orderDb.setReceivingDate(request.getReceivingDate());
        orderDb.setReceivingAddress(request.getReceivingAddress());
        orderDb.setDeliveryDate(request.getDeliveryDate());
        orderDb.setDeliveryAddress(request.getDeliveryAddress());
        orderDb.setDeliveryStatus(0);

        return orderDb;
    }
}

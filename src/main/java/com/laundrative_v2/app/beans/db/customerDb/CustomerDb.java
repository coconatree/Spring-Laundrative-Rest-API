package com.laundrative_v2.app.beans.db.customerDb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "musteri")
@Getter
@Setter
@ToString
@JsonIgnoreProperties
public class CustomerDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "adi")
    private String name;

    @Column(name = "sifre")
    private String password;

    @Column(name = "telefon")
    private String telephone;
    @Column(name = "email")
    private String email;
    @Column(name = "olusturma_tarih")
    private Date creationDate;
    @Column(name = "guncellenme_tarih")
    private Date updateDate;
    @Column(name = "musteri_notu")
    private String customerNote;
    @Column(name = "indirim_tipi")
    private Integer discountType;
    @Column(name = "indirim_orani")
    private Integer discountPercentage;

    @Column(name = "secret_key")
    private String secretKey;

    public CustomerDb() {}

    public static boolean valid(CustomerDb customerDb, String db, String client)
    {
        System.out.println("isNull : " + isNull(customerDb));
        System.out.println("matches : " + passwordMatches(db, client));

        return isNull(customerDb) && passwordMatches(db, client);
    }
    private static boolean isNull(CustomerDb customer)
    {
        return !(customer == null);
    }

    private static boolean passwordMatches(String db, String client)
    {
        return db.equals(client);
    }
}

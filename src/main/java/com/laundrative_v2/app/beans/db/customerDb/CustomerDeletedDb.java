package com.laundrative_v2.app.beans.db.customerDb;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionCategoryDb;
import com.laundrative_v2.app.beans.db.institutionDb.InstitutionServiceDb;
import com.laundrative_v2.app.beans.db.institutionDb.InstitutionWorkingDb;
import com.laundrative_v2.app.beans.json.customer.request.CustomerDelReq;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "silinen_musteri")
public class CustomerDeletedDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adi")
    private String name;
    @Column(name = "telefon")
    private String telephone;
    @Column(name = "email")
    private String email;
    @Column(name = "olus_tarih")
    private Date creationDate;
    @Column(name = "guncel_tarih")
    private Date updateDate;
    @Column(name = "silinme_tarih")
    private Date deletionDate;
    @Column(name = "kurum_id")
    private Long institutionId;
    @Column(name = "musteri_notu")
    private String customerNote;
    @Column(name = "indirim_tipi")
    private Integer discountType;
    @Column(name = "indirim_oranı")
    private BigDecimal discountPercentage;
    @Column(name = "musteri_silinme_nedeni")
    private String deletionReason;
    @Column(name = "eski_musteri_id")
    private Long oldCustomerId;
    @Column(name = "musteri_silinme_nedeni_baslık")
    private String deletionTitle;

    public static CustomerDeletedDb from(CustomerDb oldCustomer, CustomerDelReq form)
    {
        CustomerDeletedDb customerDeleted = new CustomerDeletedDb();

        customerDeleted.setName(oldCustomer.getName());
        customerDeleted.setTelephone(oldCustomer.getTelephone());
        customerDeleted.setEmail(oldCustomer.getEmail());
        customerDeleted.setCreationDate(oldCustomer.getCreationDate());
        customerDeleted.setUpdateDate(oldCustomer.getUpdateDate());
        customerDeleted.setDeletionDate(new Date(System.currentTimeMillis()));

        //TODO
        // Ask about the following line

        customerDeleted.setInstitutionId(null);

        customerDeleted.setCustomerNote(oldCustomer.getCustomerNote());
        customerDeleted.setDiscountType(oldCustomer.getDiscountType());
        customerDeleted.setDiscountPercentage(oldCustomer.getDiscountPercentage());
        customerDeleted.setDeletionReason(form.getReason());
        customerDeleted.setOldCustomerId(oldCustomer.getId());
        customerDeleted.setDeletionTitle(form.getTitle());


        return customerDeleted;
    }
}

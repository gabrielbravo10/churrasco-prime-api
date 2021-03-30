package com.churrascoprime.api.models;

import javax.persistence.*;

@Entity
@Table(name = "tb_telephone")
public class TelephoneModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telephone")
    private Long idTelephone;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @ManyToOne
    @JoinColumn(name = "id_customer", foreignKey = @ForeignKey(name = "fk_telephone_customer"))
    private CustomerModel customer;

    public Long getIdTelephone() {
        return idTelephone;
    }

    public void setIdTelephone(Long idTelephone) {
        this.idTelephone = idTelephone;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public void update(TelephoneModel updatedTelephoneModel) {
        this.telephoneNumber = getTelephoneNumber();
        this.customer = getCustomer();
    }

}

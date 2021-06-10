package com.churrascoprime.api.models;

import javax.persistence.*;

@Entity
@Table(name = "tb_address")
public class AddressModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long idAddress;

    @Column(name = "zip_code")
    private String zipCode;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    @ManyToOne
    @JoinColumn(name = "id_city",
            foreignKey = @ForeignKey(name = "fk_address_city"))
    private CityModel city;

    @ManyToOne
    @JoinColumn(name = "id_customer",
            foreignKey = @ForeignKey(name = "fk_address_customer"))
    private CustomerModel customer;

//    @OneToOne
//    @PrimaryKeyJoinColumn
//    private OrderModel order;

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

//    public OrderModel getOrder() {
//        return order;
//    }
//
//    public void setOrder(OrderModel order) {
//        this.order = order;
//    }

    public void update(AddressModel updateAddressModel) {
        this.zipCode = updateAddressModel.getZipCode();
        this.street = updateAddressModel.getStreet();
        this.number = updateAddressModel.getNumber();
        this.complement = updateAddressModel.getComplement();
        this.neighborhood = updateAddressModel.getNeighborhood();
        this.city = updateAddressModel.getCity();
        this.customer = updateAddressModel.getCustomer();
    }
}

package com.churrascoprime.api.models;

import javax.persistence.*;

@Entity
@Table(name = "tb_address")
public class AddressModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long idAddress;

    private String zipCode;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    @ManyToMany
    @JoinColumn(name = "id_city", foreignKey = @ForeignKey(name = "id_city"))
    private CityModel city;

    @ManyToMany
    @JoinColumn(name = "id_customer", foreignKey = @ForeignKey(name = "id_customer"))
    private CustomerModel customer;

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel cityModel) {
        this.city = cityModel;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customerModel) {
        this.customer = customerModel;
    }

    public void update(AddressModel updateAddressModel) {
        this.idAddress = updateAddressModel.getIdAddress();
        this.zipCode = updateAddressModel.getZipCode();
        this.street = updateAddressModel.getStreet();
        this.number = updateAddressModel.getNumber();
        this.complement = updateAddressModel.getComplement();
        this.neighborhood = updateAddressModel.getNeighborhood();
    }
}

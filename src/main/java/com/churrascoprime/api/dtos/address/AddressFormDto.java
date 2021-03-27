package com.churrascoprime.api.dtos.address;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressFormDto {

    private Long idAddress;

    @NotNull
    @Size(min = 8, max = 9)
    private String zipCode;

    @NotNull
    @Size(min = 5, max = 100)
    private String street;

    @NotNull
    @Size(min = 2, max = 5)
    private String number;

    @NotNull
    @Size(min = 5, max = 100)
    private String neighborhood;

    private String complement;

    @NotNull
    private Long city;

    @NotNull
    private Long customer;

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

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }
}

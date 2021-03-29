package com.churrascoprime.api.dtos.telephone;

import com.churrascoprime.api.dtos.customer.CustomerDto;

public class TelephoneDto {
    
    private Long idTelephone;
    private Long telephoneNumber;
    private CustomerDto customer;

    public Long getIdTelephone() {
        return idTelephone;
    }

    public void setIdTelephone(Long idTelephone) {
        this.idTelephone = idTelephone;
    }

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

}

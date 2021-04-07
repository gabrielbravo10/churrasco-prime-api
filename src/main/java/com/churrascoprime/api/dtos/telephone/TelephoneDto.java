package com.churrascoprime.api.dtos.telephone;

public class TelephoneDto {
    
    private Long idTelephone;
    private String telephoneNumber;
//    private CustomerDto customer;

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

//    public CustomerDto getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(CustomerDto customer) {
//        this.customer = customer;
//    }

}

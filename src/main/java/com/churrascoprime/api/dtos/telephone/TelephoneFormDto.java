package com.churrascoprime.api.dtos.telephone;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TelephoneFormDto {

    private Long idTelephone;

    @NotNull
    @Size(min = 8, max = 9)
    private Long telephoneNumber;

    @NotNull
    private Long customer;

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

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

}

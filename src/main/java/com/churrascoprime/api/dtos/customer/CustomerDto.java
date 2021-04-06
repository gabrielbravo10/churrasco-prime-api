package com.churrascoprime.api.dtos.customer;

import com.churrascoprime.api.dtos.telephone.TelephoneDto;

import java.util.Set;

public class CustomerDto {

    private Long idCustomer;
    private String firstName;
    private String lastName;
    private String email;
//    private Set<TelephoneDto> telephones;

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Set<TelephoneDto> getTelephones() {
//        return telephones;
//    }
//
//    public void setTelephones(Set<TelephoneDto> telephones) {
//        this.telephones = telephones;
//    }
}

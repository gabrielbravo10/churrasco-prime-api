package com.churrascoprime.api.dtos.customer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerFormDto {
    
    private Long idCustomer;
    
    @NotNull
    @Size(min = 11, max =  11)
    private String cpf;

    @NotNull
    @Size(min = 3, max =  100)
    private String firstName;

    @NotNull
    @Size(min = 3, max =  100)
    private String lasttName;

    @NotNull
    @Size(min = 15, max =  50)
    private String email;

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasttName() {
        return lasttName;
    }

    public void setLasttName(String lasttName) {
        this.lasttName = lasttName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

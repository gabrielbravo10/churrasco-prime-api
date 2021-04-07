package com.churrascoprime.api.models;

import javax.persistence.*;

@Entity
@Table(name = "tb_customer")
public class CustomerModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;

    private String cpf;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
//    private Set<TelephoneModel> telephones = new HashSet<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<TelephoneModel> getTelephones() {
//        if (telephones == null) {
//            Collections.emptySet();
//        }
//        return telephones;
//    }
//
//    public void addTelephone(TelephoneModel telephone) {
//        if (telephones == null) {
//            telephones = new HashSet<>();
//        }
//        telephones.add(telephone);
//    }

    public void update(CustomerModel updatedCustomer) {
        this.cpf = updatedCustomer.getCpf();
        this.firstName = updatedCustomer.getFirstName();
        this.lastName = updatedCustomer.getLastName();
        this.email = updatedCustomer.getEmail();
        this.password = updatedCustomer.getPassword();
//        this.telephones = updatedCustomer.getTelephones();
    }
}

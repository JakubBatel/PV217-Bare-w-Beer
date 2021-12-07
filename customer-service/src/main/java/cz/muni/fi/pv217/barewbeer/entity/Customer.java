package cz.muni.fi.pv217.barewbeer.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(nullable = false)
    public String firstName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(nullable = false)
    public String surname;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(nullable = false)
    public String email;

    public String phone;

    public void merge(Customer customer) {
        firstName = customer.firstName;
        surname = customer.surname;
        email = customer.email;
        phone = customer.phone;
    }
}

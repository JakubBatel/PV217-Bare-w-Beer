package cz.muni.fi.pv217.barewbeer.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Customer extends PanacheEntity {

    public Customer(String username, String firstName, String surname, String email, String phone) {
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public Customer() {

    }

    public String username;

    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    public String firstName;

    @NotNull(message = "Surname cannot be null")
    @NotEmpty(message = "Surname cannot be empty")
    public String surname;

    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    public String email;

    public String phone;

    public void merge(Customer customer) {
        username = customer.username;
        firstName = customer.firstName;
        surname = customer.surname;
        email = customer.email;
        phone = customer.phone;
    }
}

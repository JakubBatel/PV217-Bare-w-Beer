package cz.muni.fi.pv217.barewbeer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    public Long id;

    public String firstName;

    public String surname;

    public String email;

    public String phone;

    public void merge(Customer customer) {
        firstName = customer.firstName;
        surname = customer.surname;
        email = customer.email;
        phone = customer.phone;
    }
}

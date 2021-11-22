package muni.fi.pv217.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Customer extends PanacheEntity {
    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
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
}

package cz.muni.fi.pv217.barewbeer.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "OrderItems")
public class OrderItem {
    @Id
    @GeneratedValue
    public Long id;

    public long productId;

    public BigDecimal pricePerUnit;

    public int count;

    @JsonIgnore
    @ManyToOne
    public Order order;
}

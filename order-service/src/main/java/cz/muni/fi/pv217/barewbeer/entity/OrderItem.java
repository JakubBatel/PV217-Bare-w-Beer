package cz.muni.fi.pv217.barewbeer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    @Column(name = "product_id", nullable = false)
    public long productId;

    @NotNull
    @Min(0)
    @Column(name = "price_per_unit", nullable = false)
    public long pricePerUnit;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    public int count;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    public Order order;
}

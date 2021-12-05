package cz.muni.fi.pv217.barewbeer.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    public BigDecimal pricePerUnit;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    public int count;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    public Order order;
}

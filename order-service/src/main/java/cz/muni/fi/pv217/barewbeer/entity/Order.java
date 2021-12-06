package cz.muni.fi.pv217.barewbeer.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    public Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    public List<OrderItem> items;

    @CreationTimestamp
    public LocalDateTime creationTimestamp;


    @UpdateTimestamp
    public LocalDateTime updateTimestamp;

    @NotNull
    @Column(nullable = false)
    public boolean confirmed;

    @NotNull
    @Column(nullable = false)
    public boolean shippedOut;

    public void merge(Order order) {
        if (!confirmed) {
            confirmed = order.confirmed;
        }
        if (!shippedOut) {
            shippedOut = order.shippedOut;
        }
    }
}

package cz.muni.fi.pv217.barewbeer.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;

import cz.muni.fi.pv217.barewbeer.util.Category;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Product extends PanacheEntity {
    public String name;
    public String description;
    public BigDecimal price;
    public List<Category> categories;

    public void merge(Product product) {

    }
}

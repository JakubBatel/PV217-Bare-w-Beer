package cz.muni.fi.pv217.barewbeer.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import cz.muni.fi.pv217.barewbeer.util.Category;

@Entity
public class Product {

    @Id
    @GeneratedValue
    public Long id;
   
    public String name;
   
    public String description;
   
    public BigDecimal price;
   
    @Enumerated(EnumType.STRING)
    public List<Category> categories;

    public void merge(Product product) {
        name = product.name;
        description = product.description;
        price = product.price;
        categories = product.categories;
    }

}

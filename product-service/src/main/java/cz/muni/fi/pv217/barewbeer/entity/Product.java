package cz.muni.fi.pv217.barewbeer.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import cz.muni.fi.pv217.barewbeer.util.Category;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String description;

    public BigDecimal price;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    public List<Category> categories;

    public void merge(Product product) {
        name = product.name;
        description = product.description;
        price = product.price;
        categories = new ArrayList<Category>(product.categories);
    }

}

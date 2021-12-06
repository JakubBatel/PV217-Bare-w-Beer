package cz.muni.fi.pv217.barewbeer.entity;

import cz.muni.fi.pv217.barewbeer.util.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String description;

    public long price;

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

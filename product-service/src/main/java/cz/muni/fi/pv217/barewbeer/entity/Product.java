package cz.muni.fi.pv217.barewbeer.entity;

import cz.muni.fi.pv217.barewbeer.util.Category;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "product_name", nullable = false)
    public String name;

    @Column(name = "product_description", nullable = false)
    public String description;

    @NotNull
    @Min(0)
    @Column(nullable = false)
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

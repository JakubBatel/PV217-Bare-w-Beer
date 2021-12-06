package cz.muni.fi.pv217.barewbeer.client;

import java.math.BigDecimal;

public class Product {
    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long id;
    public String name;
    public String description;
    public BigDecimal price;
}

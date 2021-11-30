package cz.muni.fi.pv217.barewbeer.utils;

import java.math.BigDecimal;

public class ShoppingCartItem {
    private long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int count;

    public ShoppingCartItem(long productId, String name, String description, BigDecimal price, int count) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
}
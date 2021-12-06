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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + count;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + (int) (productId ^ (productId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShoppingCartItem other = (ShoppingCartItem) obj;
        if (count != other.count)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (productId != other.productId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem [count=" + count + ", description=" + description + ", name=" + name + ", price="
                + price + ", productId=" + productId + "]";
    }
}

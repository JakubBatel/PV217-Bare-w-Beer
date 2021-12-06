package cz.muni.fi.pv217.barewbeer.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ShoppingCart {
    private ArrayList<ShoppingCartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<ShoppingCartItem>();
    }

    public ArrayList<ShoppingCartItem> getItems() {
        return items;
    }

    public void addItem(ShoppingCartItem item) {
        items.add(item);
    }

    public boolean removeItem(ShoppingCartItem item) {
        return items.remove(item);
    }

    public boolean removeItem(long productId) {
        ShoppingCartItem item = items.stream().filter(x -> x.getProductId() == productId).collect(Collectors.toList()).get(0);
        return items.remove(item);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((items == null) ? 0 : items.hashCode());
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
        ShoppingCart other = (ShoppingCart) obj;
        if (items == null) {
            if (other.items != null)
                return false;
        } else if (!items.equals(other.items))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ShoppingCart [items=" + items + "]";
    }
}

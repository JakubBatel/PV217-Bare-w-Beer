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
}

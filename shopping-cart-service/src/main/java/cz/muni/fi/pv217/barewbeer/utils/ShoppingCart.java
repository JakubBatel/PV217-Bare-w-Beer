package cz.muni.fi.pv217.barewbeer.utils;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ShoppingCartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<ShoppingCartItem>();
    }

    public ArrayList<ShoppingCartItem> getItems() {
        return items;
    }

    public void addItem(ShoppingCartItem item) {
        this.items.add(item);
    }

    public boolean removeItem(ShoppingCartItem item) {
        return this.items.remove(item);
    }
}

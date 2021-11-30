package cz.muni.fi.pv217.barewbeer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import cz.muni.fi.pv217.barewbeer.utils.ShoppingCart;
import cz.muni.fi.pv217.barewbeer.utils.ShoppingCartItem;

@ApplicationScoped
public class ShoppingCartService {
    
    private Map<Long, ShoppingCart> shoppingCarts = new HashMap<Long, ShoppingCart>();

    public Map<Long, ShoppingCart> getAllShoppingCarts() {
        return shoppingCarts;
    }

    public ShoppingCart getShoppingCartForCustomer(long customerId) {
        return shoppingCarts.get(customerId);
    }

    public void addItemToShoppingCart(long customerId, ShoppingCartItem item) {
        if (!shoppingCarts.containsKey(customerId)) {
            shoppingCarts.put(customerId, new ShoppingCart());
        }

        shoppingCarts.get(customerId).addItem(item);
    }

    public boolean removeItemFromShoppingCart(long customerId, long productId) {
        if (!shoppingCarts.containsKey(customerId)) {
            return false;
        }

        return shoppingCarts.get(customerId).removeItem(productId);
    }

    public boolean clearShoppingCart(long customerId) {
        if (!shoppingCarts.containsKey(customerId)) {
            return false;
        }

        shoppingCarts.remove(customerId);
        return true;
    }

    public boolean updateItemCountInShoppingCart(long customerId, long productId, int count) {
        if (!shoppingCarts.containsKey(customerId)) {
            return false;
        }

        ArrayList<ShoppingCartItem> cartItems = shoppingCarts.get(customerId).getItems();
        ShoppingCartItem itemToUpdate = cartItems
            .stream()
            .filter(x -> x.getProductId() == productId)
            .collect(Collectors.toList()).get(0);

        cartItems.get(cartItems.indexOf(itemToUpdate)).setCount(count);
        return true;
    }
}

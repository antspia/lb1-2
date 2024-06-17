package org.example.lab_1;

import lombok.Getter;
import lombok.NonNull;

import java.util.*;

@Getter
public class User {
    private final Integer id;
    private final String username;
    private Map<Product, Integer> cart;
    private final List<Order> orderHistory;

    public User(int id, @NonNull String username){
        this.id = id;
        this.username = username;
        cart = new HashMap<>();
        orderHistory = new ArrayList<>();
    }

    public void addToCart(@NonNull Product product, @NonNull Integer amount){
        cart.put(product, amount);
    }

    public void removeFromCart(@NonNull Product product){
        if(cart.containsKey(product)){
            cart.remove(product, 1);
        }
    }

    public void clearCart(){
        cart = new HashMap<>();
    }

    @Override
    public String toString() {
        return "User - " + username + " | id - " + id + "\n\tCart:\n" + cart;
    }

    public void saveToHistory(Order newOrder) {
        orderHistory.add(newOrder);
    }

    public List<Integer> getCartProductIds() {
        List<Integer> productIds = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            productIds.add(product.getId());
        }
        return productIds;
    }
}

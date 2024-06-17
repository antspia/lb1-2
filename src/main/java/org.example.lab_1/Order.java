package org.example.lab_1;

import lombok.NonNull;

import java.util.Map;
import java.util.UUID;

public class Order {
    private final String id;
    private final Integer userId;
    private final Map<Product, Integer> orderDetails;
    private double totalPrice;

    public Order(int userId, @NonNull Map<Product, Integer> orderDetails){
        id = UUID.randomUUID().toString();
        this.userId = userId;
        this.orderDetails = orderDetails;
        calculateTotalPrice();
    }

    private void calculateTotalPrice(){
        for (Product product : orderDetails.keySet())
        {
            totalPrice += product.getPrice() * orderDetails.get(product);
        }
    }

    @Override
    public String toString() {
        return "Order id - " + id + " | userId - " + userId + "\n| Order details - " + orderDetails + "\n| Total price - " + totalPrice;
    }
}

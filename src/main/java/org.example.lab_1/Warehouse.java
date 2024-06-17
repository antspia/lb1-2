package org.example.lab_1;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Warehouse {
    @Getter
    private static List<Product> storage = new ArrayList<>();

    public static Optional<Product> findByName(String targetProductName){
        return storage.stream()
                .filter(product -> product.getName().equals(targetProductName)).findFirst();
    }

    public static void add(Product product){
        storage.add(product);
    }
}

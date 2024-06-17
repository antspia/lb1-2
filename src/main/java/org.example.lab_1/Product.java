package org.example.lab_1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ToString @Getter
public class Product {
    private int id = 0;
    private final String name;
    private double price;
    private final String description;
    private List<Category> categories;

    public Product(String name, double price, String description) {
        id += 1;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    private boolean isCategoryExist(Category category){
        return this.categories.contains(category);
    }

    public boolean addCategory(Category... categories) {
        if (Arrays.stream(categories).noneMatch(this::isCategoryExist)) {
            this.categories.addAll(Arrays.asList(categories));
            return true;
        }
        return false;
    }

    public boolean removeCategory(Category... categories){
        if (Arrays.stream(categories).allMatch(this::isCategoryExist)) {
            this.categories.removeAll(Arrays.asList(categories));
            return true;
        }
        return false;
    }
}

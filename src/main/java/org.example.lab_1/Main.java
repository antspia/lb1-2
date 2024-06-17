package org.example.lab_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Warehouse.add(new Product("Asus rog strix", 12999.99,
                "Чудовий ноутбук, але грієьтся"));
        Warehouse.add(new Product("Якісь навушники", 1299.99,
                "Нє, ну. Нормальні навушники"));
        Warehouse.add(new Product("Килимок для мишки", 399.99,
                "40х90см"));
        Warehouse.add(new Product("Acer swift 3", 20099.99,
                "Ноутбук на якому зроблена ця лабораторна робота"));
        Warehouse.add(new Product("A", 1,
                "Для тестування пошуку"));

        User mainUser = new User(0, "Vladyslav");
        while (true) {
            System.out.println("""
            \nВиберіть опцію:
            1 - Переглянути список товарів
            2 - Пошук товарів за категорією
            3 - Додати товар до кошика
            4 - Видалити з кошика
            5 - Переглянути кошик
            6 - Зробити замовлення
            7 - Переглянути історію замовлень
            0 - Вийти
            """);

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(Warehouse.getStorage());
                    break;
                case 2:
                    System.out.println("Введіть назву товару який ви шукаєте");
                    String targetProductName = scanner.next();
                    if (Warehouse.getStorage().isEmpty()){
                        System.out.println("Вибачте, але немає товару з такою назвою");
                        break;
                    }
                    System.out.println(Warehouse.getStorage().stream()
                            .filter(product -> product.getName().equals(targetProductName)).findFirst());
                    break;
                case 3:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int id = scanner.nextInt();
                    if (id > Warehouse.getStorage().size()) {
                        System.out.println("Немає такого продукту :( ");
                        break;
                    }
                    mainUser.addToCart(Warehouse.getStorage().get(id), 1);
                    System.out.println("Товар " + Warehouse.getStorage().get(id) + " додано до кошика");
                    break;
                case 4:
                    System.out.println(mainUser.getCart());
                    int productID = -1;
                    while(!mainUser.getCartProductIds().contains(productID)){
                        System.out.println("Введіть id товару який ви хочете видалити з кошика");
                        productID = scanner.nextInt();
                    }
                    mainUser.removeFromCart(Warehouse.getStorage().get(productID));
                    break;
                case 5:
                    System.out.println(mainUser.getCart());
                    break;
                case 6:
                    if(mainUser.getCart().isEmpty()){
                        System.out.println("Ваш кошик пустий, додайте якісь товари");
                        break;
                    }
                    System.out.println("Замовлення оформлено!");
                    mainUser.saveToHistory(new Order(mainUser.getId(), mainUser.getCart()));
                    mainUser.clearCart();
                    break;
                case 7:
                    System.out.println(mainUser.getOrderHistory());
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}
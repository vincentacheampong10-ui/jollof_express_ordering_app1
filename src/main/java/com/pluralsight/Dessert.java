package com.pluralsight;

import java.util.Scanner;

public class Dessert {
    private String name;
    private double price;

    public Dessert(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static Dessert createFromUserInput(Scanner scanner) {
        System.out.println("Choose dessert (1.Cake 8, 2.Chin Chin 6, 3.Fruit Cup 7, 4.None 0): ");
        switch (scanner.nextLine()) {
            case "2": return new Dessert("Chin Chin", 6);
            case "3": return new Dessert("Fruit Cup", 7);
            default: return new Dessert("Cake Slice", 8);
        }
    }

    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " (GHS " + price + ")";
    }
}

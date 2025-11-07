package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JollofMeal extends Meal {
    private String type;
    private double basePrice;
    private String size;
    private double sizeMultiplier;
    private String riceType;
    private double riceCost;
    private String protein;
    private double proteinCost;
    private List<AddOn> addOns = new ArrayList<>();

    public JollofMeal(String name, double basePrice) {
        super(name, basePrice);
    }

    @Override
    public double calculatePrice() {
        double addOnTotal = 0;
        for (AddOn addOn : addOns) addOnTotal += addOn.getCost();
        return (basePrice + riceCost + proteinCost + addOnTotal) * sizeMultiplier;
    }

    public static JollofMeal createFromUserInput(Scanner scanner) {
        System.out.println("\n--- Step 1: Choose Jollof Type ---");
        System.out.println("1. Classic (25), 2. Coconut (30), 3. Party (35), 4. Veg (27)");
        int choice = Integer.parseInt(scanner.nextLine());
        String type;
        double base;
        switch (choice) {
            case 1:
                type = "Classic Jollof";
                base = 30;
                break;
            case 2:
                type = "Coconut Jollof";
                base = 35;
                break;
            case 3:
                type = "Party Jollof";
                base = 27;
                break;
            case 4:
                type = "Vegetarian Jollof";
                base = 25;
                break;
            default:
                System.out.println("Choose an option above");
                break;
        }
        return null;
    }
}




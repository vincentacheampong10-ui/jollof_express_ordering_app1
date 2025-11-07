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

    public void setSize(String size, double multiplier) {
        this.size = size;
        this.sizeMultiplier = multiplier;
    }

    public void setProtein(String protein, double cost) {
        this.protein = protein;
        this.proteinCost = cost;
    }

    public void addAddOn(AddOn addOn) {
        if (addOn != null) {
            this.addOns.add(addOn);
        }
    }


    public static JollofMeal createFromUserInput(Scanner scanner) {

        String type = "";
        double base = 0.0;

        System.out.println("\n--- Step 1: Choose Jollof Type ---");
        System.out.println("1. Classic Jollof (25), 2. Coconut Jollof (30), 3.Party Jollof (35), 4. Vegetarian Jollof (27)");
        System.out.print("Enter choice:");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                type = "Classic Jollof";
                base = 25;
                break;
            case 2:
                type = "Coconut Jollof";
                base = 30;
                break;
            case 3:
                type = "Party Jollof";
                base = 35;
                break;
            case 4:
                type = "Vegetarian Jollof";
                base = 27;
                break;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return null;
        }

        JollofMeal jollof = new JollofMeal(type, base);
        jollof.basePrice = base;
        return jollof;
    }

}






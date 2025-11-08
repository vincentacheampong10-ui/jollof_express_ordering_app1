package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JollofMeal extends Meal {
    //  private String type;
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
        this.basePrice = basePrice;
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

        JollofMeal jollof = null;
        String mealType = "";
        double mealBasePrice = 0.0;

        // --- Step 1: Choose Jollof Base Type
        System.out.println("\n--- Step 1: Choose Jollof Type ---");
        System.out.println("1. Classic Jollof (25), 2. Coconut Jollof (30), 3. Party Jollof (35), 4. Vegetarian Jollof (27)");
        System.out.print("Enter choice: ");

        int typeChoice;
        try {
            typeChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }

        switch (typeChoice) {
            case 1:
                mealType = "Classic Jollof";
                mealBasePrice = 25;
                break;
            case 2:
                mealType = "Coconut Jollof";
                mealBasePrice = 30;
                break;
            case 3:
                mealType = "Party Jollof";
                mealBasePrice = 35;
                break;
            case 4:
                mealType = "Vegetarian Jollof";
                mealBasePrice = 27;
                break;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return null;
        }

        jollof = new JollofMeal(mealType, mealBasePrice);
        // The basePrice field is now set via the constructor.

        // --- Step 2: Choose Size ---
        System.out.println("\n--- Step 2: Choose Size ---");
        System.out.println("1. Regular (x1.0), 2. Large (x1.5)");
        System.out.print("Enter choice: ");

        String sizeChoice = scanner.nextLine();

        if (sizeChoice.equals("2")) {
            jollof.setSize("Large", 1.5);
        } else {
            jollof.setSize("Regular", 1.0);
            if (!sizeChoice.equals("1")) {
                System.out.println("Invalid size selected. Defaulting to Regular.");
            }
        }

        // --- Step 3: Choose Protein ---
        System.out.println("\n--- Step 3: Choose Protein ---");
        System.out.println("1. Chicken (+8), 2. Beef (+10), 3. Fish (+12), 4. None (+0)");
        System.out.print("Enter choice: ");

        String proteinChoice = scanner.nextLine();

        switch (proteinChoice) {
            case "1":
                jollof.setProtein("Chicken", 8.0);
                break;
            case "2":
                jollof.setProtein("Beef", 10.0);
                break;
            case "3":
                jollof.setProtein("Fish", 12.0);
                break;
            case "4":
                jollof.setProtein("None", 0.0);
                break;
            default:
                System.out.println("Invalid protein selected. Defaulting to None.");
                jollof.setProtein("None", 0.0);
                break;
        }

        // --- Step 4: Add Add-ons
        boolean addingAddOns = true;
        while (addingAddOns) {
            System.out.println("\n--- Step 4: Add Add-ons ---");
            System.out.println("1. Plantain (4.0), 2. Egg (3.0), 3. Coleslaw (2.0), 0. Done");
            System.out.print("Enter choice: ");
            String addOnChoice = scanner.nextLine();

            switch (addOnChoice) {

                case "1":
                    jollof.addAddOn(new AddOn("Plantain", 4.0));
                    break;
                case "2":
                    jollof.addAddOn(new AddOn("Egg", 3.0));
                    break;
                case "3":
                    jollof.addAddOn(new AddOn("Coleslaw", 2.0));
                    break;
                case "0":
                    addingAddOns = false;
                    break;
                default:
                    System.out.println("Invalid choice. Choose an option above.");
                    break;
            }
        }
        return jollof; // Returns the fully customized object
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // 1. Append the Base Meal and Size
        sb.append(this.name)
                .append(" (GHS ")
                .append(String.format("%.2f", this.basePrice))
                .append(") - Size: ").append(this.size);

        // 2. Append Protein
        sb.append(", Protein: ").append(this.protein);

        // 3. Append Add-ons (requires AddOn.getName())
        if (!addOns.isEmpty()) {
            sb.append(", Add-ons: ");
            for (int i = 0; i < addOns.size(); i++) {
                sb.append(addOns.get(i).getName()); // Make sure AddOn has getName()
                if (i < addOns.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        // 4. Append the Final Calculated Price
        sb.append(" [Total Meal Cost: GHS ")
                .append(String.format("%.2f", this.calculatePrice()))
                .append("]");

        return sb.toString();
    }
}
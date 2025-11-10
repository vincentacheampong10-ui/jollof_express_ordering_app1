package com.pluralsight;

public class Combo extends Meal {

    // Inherit the constructor from Meal
    public Combo(String name, double basePrice) {
        super(name, basePrice);
    }

    @Override
    public double calculatePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return name + " Combo [Fixed Price: GHS " + String.format("%.2f", basePrice) + "]";
    }
}

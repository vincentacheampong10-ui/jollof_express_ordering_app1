package com.pluralsight;

public class AddOn {
    private String name;
    private double cost;

    public AddOn(String name, boolean premium) {
        this.name = name;
        this.cost = premium ? 6 : 3; ///true : false
    }

    public AddOn(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }


    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " (GHS " + String.format("%.2f", cost) + ")";
    }
}



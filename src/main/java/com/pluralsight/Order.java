package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Meal> meals = new ArrayList<>();
    private Drink drink;
    private Dessert dessert;

    public Order(List<Meal> meals, Drink drink, Dessert dessert) {
        this.meals = meals;
        this.drink = drink;
        this.dessert = dessert;
    }
    public Order() {

    }



    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    public double calculateTotal() {
        double total = 0;
        for (Meal meal : meals) total += meal.calculatePrice();
        if (drink != null) total += drink.getPrice();
        if (dessert != null) total += dessert.getPrice();
        return total;

    }
}
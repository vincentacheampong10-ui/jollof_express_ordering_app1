package com.pluralsight;

import java.util.Scanner;


public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Order currentOrder = new Order();

    public void displayHome() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== 🍛 JOLLOF EXPRESS MENU ===");
            System.out.println("1.New Order");
            System.out.println("0.Exit");
            System.out.print("Enter choice:");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    startNewOrder();
                    break;
                case "0":
                    System.out.println("Goodbye! 👋");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void startNewOrder() {
        currentOrder = new Order();

        boolean ordering = true;
        while (ordering) {
            System.out.println("\n--- ORDER MENU ---");
            System.out.println("1. Add Jollof Meal");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Side");
            System.out.println("4. Checkout");
            System.out.println("0. Cancel Order");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    JollofMeal jollof = JollofMeal.createFromUserInput(scanner);
                    if (jollof != null) {
                        currentOrder.addMeal(jollof);
                    } else {
                        System.out.println("Jollof Meal not added.");
                    }
                    break;
                case "2":
                    Drink drink = Drink.createFromUserInput(scanner);
                    if (drink != null) {
                        currentOrder.setDrink(drink);
                    } else {
                        System.out.println("Drink not added.");
                    }
                    break;
                case "3":
                    Dessert dessert = Dessert.createFromUserInput(scanner);
                    if (dessert != null) {
                        currentOrder.setDessert(dessert);
                    } else {
                        System.out.println("Dessert not added due to invalid input.");
                    }
                    break;
                case "4":
                    if (currentOrder.calculateTotal() > 0) {
                        checkout();
                        ordering = false;
                    } else {
                        System.out.println("Order is empty! Please add an item before checking out.");
                    }
                    break;
                case "0":
                    System.out.println("Order canceled.");
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void checkout() {
        System.out.println("\n===== ORDER SUMMARY =====");
        System.out.println(currentOrder);
        System.out.printf("\n💰 Final Total: GHS %.2f%n", currentOrder.calculateTotal());

        System.out.print("Confirm order? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            ReceiptManager.saveReceipt(currentOrder);
        } else {
            System.out.println("Order canceled.");
        }
    }
}


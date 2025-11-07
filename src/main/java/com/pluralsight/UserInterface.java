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
                    currentOrder.addMeal(jollof);
                    break;
                case "2":
                    Drink drink = Drink.createFromUserInput(scanner);
                    currentOrder.setDrink(drink);
                    break;
                case "3":
                    Dessert dessert = Dessert.createFromUserInput(scanner);
                    currentOrder.setDessert(dessert);
                    break;
                case "4":
                    checkout();
                    ordering = false;
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
            System.out.println("✅ Order saved successfully!");
        } else {
            System.out.println("Order canceled.");
        }
    }
}


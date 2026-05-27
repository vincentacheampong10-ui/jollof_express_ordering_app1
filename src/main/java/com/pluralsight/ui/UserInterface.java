package com.pluralsight.ui;

import com.pluralsight.data.ReceiptManager;
import com.pluralsight.model.*;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Order currentOrder = new Order();


    public void displayHome() {
        boolean running = true;
        while (running) {
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║              JOLLOF EXPRESS MENU             ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║ 1.  New Order                                ║");
            System.out.println("║ 0.  Exit                                     ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Enter choice: ");
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
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║              JOLLOF EXPRESS MENU             ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║  1. Add Jollof Meal                          ║");
            System.out.println("║  2. Add Drink                                ║");
            System.out.println("║  3. Add Side (Dessert)                       ║");
            System.out.println("║  4. Checkout                                 ║");
            System.out.println("║  5. SIGNATURE Meal                           ║");
            System.out.println("║  0. Exit                                     ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    JollofMeal jollof = getJollofMealFromUser();
                    if (jollof != null) {
                        currentOrder.addMeal(jollof);
                        System.out.println("✅ " + jollof.getName() + " added to order.");
                    } else {
                        System.out.println("Jollof Meal creation failed.");
                    }
                    break;
                case "2":
                    Drink drink = Drink.createFromUserInput(scanner);
                    if (drink != null) {
                        currentOrder.setDrink(drink);
                        System.out.println("✅ " + drink.getName() + " added to order.");
                    } else {
                        System.out.println("Drink not added.");
                    }
                    break;
                case "3":
                    Dessert dessert = Dessert.createFromUserInput(scanner);
                    if (dessert != null) {
                        currentOrder.setDessert(dessert);
                        System.out.println("✅ " + dessert.getName() + " added to order.");
                    } else {
                        System.out.println("Dessert not added.");
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
                case "5":
                    selectCombo();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private JollofMeal getJollofMealFromUser() {

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                 1. CHOOSE JOLLOF TYPE            ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  1. Classic Jollof        ($25)                  ║");
        System.out.println("║  2. Coconut Jollof        ($30)                  ║");
        System.out.println("║  3. Party Jollof          ($35)                  ║");
        System.out.println("║  4. Vegetarian Jollof     ($27)                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
        String typeChoice = scanner.nextLine();

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                     2. CHOOSE SIZE               ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  1. Regular (x1.0)                               ║");
        System.out.println("║  2. Large (x1.5)                                 ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
        String sizeChoice = scanner.nextLine();

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                 3. CHOOSE PROTEIN                ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  1. Chicken (+$8)                                ║");
        System.out.println("║  2. Beef (+$10)                                  ║");
        System.out.println("║  3. Fish (+$12)                                  ║");
        System.out.println("║  4. None (+$0)                                   ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
        String proteinChoice = scanner.nextLine();


        JollofMeal jollof = JollofMeal.createFromChoices(typeChoice, sizeChoice, proteinChoice);
        if (jollof == null) return null;

        boolean addingAddOns = true;
        int premiumCount = 0;
        final int premiumLimit = 3;

        while (addingAddOns) {
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.println("║             4. ADD ADD-ONS                                 ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.println("║  Premium Limit Remaining: " + (premiumLimit - premiumCount) + " left                       ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.println("║  R) Regular Add-on      →  Fixed Cost                      ║");
            System.out.println("║  P) Premium Add-on      →  GHS 6 each                      ║");
            System.out.println("║  0) Done Adding Add-ons                                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            System.out.print("Choose R, P, or 0: ");
            String categoryChoice = scanner.nextLine().toUpperCase();

            switch (categoryChoice) {
                case "R":
                    handleRegularAddOns(jollof);
                    break;
                case "P":
                    premiumCount += handlePremiumAddOns(jollof, premiumCount, premiumLimit);
                    break;
                case "0":
                    addingAddOns = false;
                    break;
                default:
                    System.out.println("Invalid category choice. Try again.");
            }
        }
        return jollof;
    }

    private void handleRegularAddOns(JollofMeal jollof) {
        boolean choosingRegular = true;
        while (choosingRegular) {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║                 REGULAR ADD-ONS                  ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║  1) Plantain ............ (4.0)                  ║");
            System.out.println("║  2) Egg ................. (3.0)                  ║");
            System.out.println("║  3) Coleslaw ............ (2.0)                  ║");
            System.out.println("║  0) Back                                         ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
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
                    choosingRegular = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private int handlePremiumAddOns(JollofMeal jollof, int currentCount, final int limit) {
        if (currentCount >= limit) {
            System.out.println("⚠️ Premium add-on limit reached (" + limit + ").");
            return 0;
        }

        boolean choosingPremium = true;
        int addedCount = 0;

        while (choosingPremium && currentCount + addedCount < limit) {
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("║               PREMIUM ADD-ONS (GHS 6 each)           ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.printf("║  Limit Remaining: %-35s║%n", (limit - (currentCount + addedCount)));
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║  1) Fried Plantain (Kelewele)                        ║");
            System.out.println("║  2) Extra Meat Portion                               ║");
            System.out.println("║  3) Fried Yam                                        ║");
            System.out.println("║  4) Avocado Slices                                   ║");
            System.out.println("║  0) Back                                             ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    jollof.addAddOn(new AddOn("Fried Plantain (Kelewele)", true));
                    addedCount++;
                    break;
                case "2":
                    jollof.addAddOn(new AddOn("Extra Meat Portion", true));
                    addedCount++;
                    break;
                case "3":
                    jollof.addAddOn(new AddOn("Fried Yam", true));
                    addedCount++;
                    break;
                case "4":
                    jollof.addAddOn(new AddOn("Avocado Slices", true));
                    addedCount++;
                    break;
                case "0":
                    choosingPremium = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
        if (currentCount + addedCount == limit) {
            System.out.println("✅ Premium limit reached.");
        }
        return addedCount;
    }

    private void checkout() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                   🧾 ORDER SUMMARY                 ║");
        System.out.println("╠════════════════════════════════════════════════════╣");

        // Print concise, friendly lines for each meal.
        for (Meal meal : currentOrder.getMeals()) {
            if (meal instanceof Combo) {
                Combo combo = (Combo) meal;
                System.out.println("✅ " + combo.getCompactDescription());
            } else {
                // fallback: use meal.toString()
                System.out.println(meal.toString());
            }
        }

        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.printf("║  💰 FINAL TOTAL: GHS %-30.2f ║%n", currentOrder.calculateTotal());
        System.out.println("╚══════════════════════════════════════════════════════╝");

        System.out.print("\n✅ Confirm order? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            ReceiptManager.saveReceipt(currentOrder);
        } else {
            System.out.println("Order canceled.");
        }
    }

    private void selectCombo() {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         SIGNATURE MEALS                          ║");
        System.out.println("╠══════════╦═════════════════════════════════════════════╦═════════╣");
        System.out.println("║ Option   ║ Meals                                       ║ Price   ║");
        System.out.println("╠══════════╬═════════════════════════════════════════════╬═════════╣");

        System.out.printf("║ %-8s ║ %-45s ║ %5.2f ║%n", "1",
                "Classic Jollof + Chicken + Kelewele + Sobolo", 30.00);

        System.out.printf("║ %-8s ║ %-45s ║ %7.2f ║%n", "2",
                "Veg Jollof + Tofu + Salad + Fresh Juice", 85.00);

        System.out.printf("║ %-8s ║ %-45s ║ %7.2f ║%n", "3",
                "Party Jollof + Goat Meat + Shito + Malt", 20.00);

        System.out.printf("║ %-8s ║ %-45s ║ %7.2f ║%n", "4",
                "None", 0.00);

        System.out.println("╚══════════╩═════════════════════════════════════════════╩═════════╝");
        System.out.print("Enter combo choice:");
        String comboChoice = scanner.nextLine();

        Combo combo = null;
        String name;
        double price;

        switch (comboChoice) {
            case "1":
                name = "Classic Jollof + Chicken + Kelewele + Sobolo";
                price = 30.00;
                combo = new Combo(name, price);
                combo.addComponent(new JollofMeal("Classic Jollof", 25.00));
                combo.addComponent(new AddOn("Chicken", 8.0));
                combo.addComponent(new AddOn("Kelewele", true));
                combo.addComponent(new Drink("Sobolo", 9.0));
                break;
            case "2":
                name = "Veg Jollof + Tofu + Salad + Fresh Juice";
                price = 85.00;
                combo = new Combo(name, price);
                combo.addComponent(new JollofMeal("Veg Jollof", 27.00));
                combo.addComponent(new AddOn("Tofu", 8.0));
                combo.addComponent(new AddOn("Salad", true));
                combo.addComponent(new Drink("Fresh Juice", 9.0));
                break;
            case "3":
                name = "Party Jollof + Goat Meat + Shito + Malt";
                price = 90.00;
                combo = new Combo(name, price);
                combo.addComponent(new JollofMeal("Party Jollof ", 35.00));
                combo.addComponent(new AddOn("Goat Meat", 8.0));
                combo.addComponent(new AddOn("Shito", true));
                combo.addComponent(new Drink("Malt", 9.0));
                break;
            case "4":
                System.out.println("None.");
                return;
            default:
                System.out.println("Invalid Signature meal choice.");
                return;
        }

        if (combo != null) {
            currentOrder.addMeal(combo);
            // Standardized Success Message for Combo
            System.out.println("✅ " + combo.getName() + " added to order.");
            customizeCombo(combo); // Continue to customization
        }
    }

    private void customizeCombo(Combo combo) {
        boolean customizing = true;
        while (customizing) {

            System.out.println(    combo.getName().toUpperCase());
            System.out.println("╠════════════════════════════════════════════════════╣");
            System.out.println("║ 1. Add another rice                                ║");
            System.out.println("║ 2. Add another Drink                               ║");
            System.out.println("║ 3. Add another Side                                ║");
            System.out.println("║ 4. remove rice                                     ║");
            System.out.println("║ 5. remove Drink                                    ║");
            System.out.println("║ 6. remove Side                                     ║");
            System.out.println("║ 0. Done Customizing                                ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":

                    JollofMeal jollof = jollofRice();
                    if (jollof != null) {
                        combo.addComponent(jollof);
                        System.out.println("✅ " + jollof.getName() + " added to " + combo.getName() + ".");
                    }
                    break;
                case "2":
                    Drink drink = Drink.createFromUserInput(scanner);
                    if (drink != null) {
                        combo.addComponent(drink);
                        System.out.println("✅ " + drink.getName() + " added to " + combo.getName() + ".");
                    }
                    break;
                case "3":
                    Dessert dessert = Dessert.createFromUserInput(scanner);
                    if (dessert != null) {
                        combo.addComponent(dessert);
                        System.out.println("✅ " + dessert.getName() + " added to " + combo.getName() + ".");
                    }
                    break;
                case "4":
                    JollofMeal riceRemove = getJollofMealToRemove(combo);
                    if (riceRemove != null) {
                        combo.removeComponent(riceRemove);
                        System.out.println("✅ " + riceRemove.getName() + " Removed.");
                        customizing = false;
                    }
                    break;
                case "0":
                    customizing = false;
                    System.out.println("Customization complete.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private JollofMeal getJollofMealToRemove(Combo combo) {
        List<JollofMeal> currentJollofs = new ArrayList<>();

        for (Object component : combo.getComponents()) {
            if (component instanceof JollofMeal) {
                currentJollofs.add((JollofMeal) component);
            }
        }

        if (currentJollofs.isEmpty()) {
            System.out.println("⚠️ No Jollof Meals available to remove.");
            return null;
        }
        System.out.print("""
                        ╔══════════════════════════════════════════════════╗
                        ║   1. CHOOSE RICE TO REMOVE                       ║
                        ╠══════════════════════════════════════════════════╣
                        ║  1. Classic Jollof        ($25)                  ║
                        ║  2. Coconut Jollof        ($30)                  ║
                        ║  3. Party Jollof          ($35)                  ║
                        ║  4. Vegetarian Jollof     ($27)                  ║
                        ╚══════════════════════════════════════════════════╝ 
                        Enter number to remove (or 0 to cancel): """);

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= currentJollofs.size()) {
                return currentJollofs.get(choice - 1);
            } else if (choice == 0) {
                System.out.println("Removal cancelled.");
                return null;
            } else {
                System.out.println("❌ Invalid selection number.");
                return null;
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Please enter a number.");
            scanner.nextLine();
            return null;
        }

    }

    private JollofMeal jollofRice() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                 1. CHOOSE JOLLOF TYPE            ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  1. Classic Jollof        ($25)                  ║");
        System.out.println("║  2. Coconut Jollof        ($30)                  ║");
        System.out.println("║  3. Party Jollof          ($35)                  ║");
        System.out.println("║  4. Vegetarian Jollof     ($27)                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
        String typeChoice = scanner.nextLine();

        String name;
        double price;

        switch (typeChoice) {
            case "1":
                name = "Classic Jollof";
                price = 25.00;
                break;
            case "2":
                name = "Coconut Jollof ";
                price = 30.00;
                break;
            case "3":
                name = "Party Jollof";
                price = 35.00;
                break;
            case "4":
                name = "Vegetarian Jollof";
                price = 27.00;
                break;
            default:
                System.out.println("Invalid Signature meal choice.");
                return null;
        }
        return new JollofMeal(name, price);
    }
}

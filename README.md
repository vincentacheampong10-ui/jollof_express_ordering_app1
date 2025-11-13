🍛 Jollof Express Ordering App
Overview

Jollof Express is a Java-based console application that simulates an interactive meal ordering system.
Users can create custom Jollof meals, select add-ons, choose combo deals, and calculate total prices.
The project demonstrates object-oriented programming (OOP) concepts such as classes, interfaces, encapsulation, inheritance, and polymorphism.

✨ Features

🥘 Build your own Jollof meal (choose rice type, size, and protein)

🍗 Add regular or premium add-ons

🥤 Select drinks and desserts

💰 Choose from special combo deals

📦 View and confirm order summary before checkout

💻 Interface-based architecture for flexibility and scalability

🧩 Project Structure
Class / Interface	Description
Meal (interface)	Defines core meal behavior (price calculation, add-ons).
JollofMeal	Implements Meal; handles Jollof creation and customization.
AddOn	Represents optional meal additions (regular/premium).
Drink	Represents available beverages.
Side	Represents desserts or sides.
Combo	Represents bundle deals (e.g., Jollof + Drink).
Order	Manages a list of meals, calculates totals, and prints receipts.
UserInterface	Handles all user input/output menus.
Program (Main)	Entry point; starts the application.
🧠 Concepts Demonstrated

Encapsulation – Using private fields and getters/setters.

Polymorphism – Multiple meal types implementing the same interface.

Abstraction – Interfaces defining shared behavior.

Composition – Orders composed of multiple meals and add-ons.

⚙️ How to Run

Clone or download the repository.

Open the project in your IDE (e.g., IntelliJ, Eclipse, or VS Code).

Compile and run Program.java.

Follow the on-screen prompts to create and order your meal.

🧾 Example Output
=== 🍛 JOLLOF EXPRESS MENU ===
1. New Order
0. Exit
   Enter choice: 1

--- ORDER MENU ---
1. Add Jollof Meal
2. Add Drink
3. Add Side (Dessert)
4. Checkout
0. Cancel Order

<img width="1919" height="1033" alt="Screenshot 2025-11-13 110705" src="https://github.com/user-attachments/assets/4efad1f5-b625-4829-b255-f8ae472653bd" />

🧪 Testing

You can test functionality using JUnit:

assertEquals(50.0, meal.calculatePrice(), 0.01);
assertEquals("Classic Jollof", meal.getType());

📘 Future Improvements

GUI version using JavaFX or Swing

Save and load past orders

Implement discount codes and loyalty points

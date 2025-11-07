package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {
    public static void saveReceipt(Order order) {
        try {
            File director = new File("receipts.csv");
            if (!director.exists()) director.mkdir();///director.mkdir(): If the folder doesn't exist, this command creates it. This ensures all your receipts have a place to go.

            String filename = "receipts/" + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write("=== Jollof Express Receipt ===\n");
                writer.write(order.toString());
                writer.write("\nTotal: GHS " + String.format("%.2f", order.calculateTotal()) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving receipt.");
        }
    }
}

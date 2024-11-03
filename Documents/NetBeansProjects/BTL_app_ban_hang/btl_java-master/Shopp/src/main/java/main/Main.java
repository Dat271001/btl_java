package main;

import cart.Cart;
import cart.CartItem;
import payment.Payment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart();
        Payment payment = new Payment();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Quantity");
            System.out.println("4. Display Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter size: ");
                    String size = scanner.nextLine();
                    cart.addItem(new CartItem(name, quantity, price, size));
                    break;
                case 2:
                    System.out.print("Enter product name to remove: ");
                    String removeName = scanner.nextLine();
                    cart.removeItem(removeName);
                    break;
                case 3:
                    System.out.print("Enter product name to update: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    cart.updateQuantity(updateName, newQuantity);
                    break;
                case 4:
                    cart.displayCart();
                    break;
                case 5:
                    double total = cart.getTotalCost();
                    System.out.println("Total cost: " + total);
                    payment.processPayment(total);
                    cart.clearCart();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }
}

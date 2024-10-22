package cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    // Constructor không tham số
    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(String productName) {
        items.removeIf(item -> item.getProductName().equalsIgnoreCase(productName));
    }

    public void updateQuantity(String productName, int quantity) {
        for (CartItem item : items) {
            if (item.getProductName().equalsIgnoreCase(productName)) {
                item.setQuantity(quantity);
                break;
            }
        }
    }

    public void clearCart() {
        items.clear();
    }

    public double getTotalCost() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (CartItem item : items) {
                System.out.println(item);
            }
        }
    }

    public List<CartItem> getItems() {
        return items;
    }
}

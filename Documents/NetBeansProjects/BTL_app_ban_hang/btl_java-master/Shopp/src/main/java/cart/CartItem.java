package cart;

public class CartItem {
    private String productName;
    private int quantity;
    private double price;
    private String size; // Thêm thuộc tính size

    public CartItem(String productName, int quantity, double price, String size) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
    }

    public CartItem(String name, int quantity, double price) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }
   
    public void setPrice(double price) {
        this.price = price;
    }
    

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "Product: " + productName + ", Quantity: " + quantity + ", Price: " + price + ", Size: " + size;
    }
}

package model;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private String size;
    private int stock; // Số lượng sản phẩm còn trong kho
    private String imagePath; // Đường dẫn tới hình ảnh sản phẩm
    private User seller; // Người bán của sản phẩm

    public Product(String name, double price, int quantity, String size, int stock, String imagePath) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.stock = stock; // Khởi tạo số lượng tồn kho
        this.imagePath = imagePath; // Khởi tạo đường dẫn hình ảnh
    }

    // Getter và Setter cho các thuộc tính
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Getter và Setter cho seller
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}

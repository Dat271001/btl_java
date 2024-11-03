
package model;

import java.io.File;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private String size;
    private int stock; // Số lượng sản phẩm đã bán
    private String imagePath; // Đường dẫn tới hình ảnh sản phẩm
    private User seller; // Người bán của sản phẩm
    private int quantity_sold;
    public Product(String name, double price, int quantity, String size, int stock, String imagePath) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.stock = stock; // Khởi tạo số lượng tồn kho
        this.imagePath = new File("src\\main\\java\\img\\" + imagePath).getAbsolutePath(); // Khởi tạo đường dẫn hình ảnh
        this.quantity_sold=0;
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
    public double Getprice(){
        return price * -1;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getQuantity_Sold() {
        return quantity_sold;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setQuantity_Sold(int quantity) {
        this.quantity_sold = quantity;
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

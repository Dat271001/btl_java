package model;

import java.util.List;

public class PurchaseHistory {
    private List<Product> purchasedProducts; // Danh sách sản phẩm đã mua
    private double totalAmount; // Tổng số tiền đã thanh toán
    private String purchaseDate; // Ngày mua

    public PurchaseHistory(List<Product> purchasedProducts, double totalAmount) {
        this.purchasedProducts = purchasedProducts;
        this.totalAmount = totalAmount;
        this.purchaseDate = java.time.LocalDate.now().toString(); // Ghi lại ngày mua
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }
}

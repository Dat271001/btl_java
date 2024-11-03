package model;

import controller.Cart;
public class User {
    private String username;
    private String password;
    private double balance; // Số dư tài khoản
    private String address; // Địa chỉ người dùng
    private String phone; // Số điện thoại
    private Cart cart; // Tham chiếu đến giỏ hàng

    public User(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance; // Khởi tạo số dư
        this.cart = new Cart(); // Khởi tạo giỏ hàng
    }

    // Getter và Setter
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public String getAddress() {
        return address; // Getter cho địa chỉ
    }

    public void setAddress(String address) {
        this.address = address; // Setter cho địa chỉ
    }

    public String getPhone() {
        return phone; // Getter cho số điện thoại
    }

    public void setPhone(String phone) {
        this.phone = phone; // Setter cho số điện thoại
    }

    public void addBalance(double amount) {
        this.balance += amount; // Cộng số tiền vào số dư
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean deductBalance(double amount) {
        if (amount <= balance) {
            balance -= amount; // Trừ số tiền từ số dư
            return true; // Thanh toán thành công
        }
        return false; // Không đủ tiền
    }

    public Cart getCart() {
        return cart; // Getter cho giỏ hàng
    }
}

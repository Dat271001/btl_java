package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import model.Product;
import model.PurchaseHistory;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Cart {
    private List<Product> products; // Danh sách sản phẩm trong giỏ hàng
    private List<PurchaseHistory> purchaseHistories; // Danh sách lịch sử mua hàng
    private double totalRevenue; // Tổng doanh thu từ các sản phẩm đã bán

    public Cart() {
        products = new ArrayList<>();
        purchaseHistories = new ArrayList<>();
        totalRevenue = 0; // Khởi tạo doanh thu
    }
    public void AddProduct(Product product){
         
     // Kiểm tra xem sản phẩm đã được thêm vào trước hay chưa
            boolean check = false;
            for(Product e : products){
                if(e.getName().compareTo(product.getName())==0){
                    check = true;
                    int t = e.getQuantity();
                    products.remove(e);
                    products.add(new Product(product.getName(),product.getPrice(),product.getQuantity()+t,product.getSize(),product.getStock(),product.getImagePath()));
                    break;
                }
            }
            // Nếu sản phẩm chưa được thêm thì tạo mới
            if(!check) products.add(product);
            // Sắp xếp sản phẩm theo giá tăng dần
            Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
    }
    public void addProduct(Product product) {
        if (product.getStock() <= 0) {
            JOptionPane.showMessageDialog(null, "Sản phẩm đã hết hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else if (product.getQuantity() > product.getStock()) {
            JOptionPane.showMessageDialog(null, "Số lượng vượt quá hàng tồn kho!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
     // Kiểm tra xem sản phẩm đã được thêm vào trước hay chưa
            boolean check = false;
            for(Product e : products){
                if(e.getName().compareTo(product.getName())==0){
                    check = true;
                    int t = e.getQuantity();
                    products.remove(e);
                    products.add(new Product(product.getName(),product.getPrice(),product.getQuantity()+t,product.getSize(),product.getStock(),product.getImagePath()));
                    break;
                }
            }
            // Nếu sản phẩm chưa được thêm thì tạo mới
            if(!check) products.add(product);
            // Sắp xếp sản phẩm theo giá tăng dần
            Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
            JOptionPane.showMessageDialog(null, "Sản phẩm đã được thêm vào giỏ hàng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Tính tổng tiền trong giỏ hàng
    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }

    // Thanh toán sản phẩm trong giỏ hàng
    public void checkout() {
        double totalAmount = calculateTotal(); // Tính tổng số tiền thanh toán
        if (totalAmount > 0) {
            // Lưu lịch sử mua hàng
            PurchaseHistory purchaseHistory = new PurchaseHistory(new ArrayList<>(products), totalAmount);
            purchaseHistories.add(purchaseHistory); // Thêm vào danh sách lịch sử mua hàng
            for (Product product : products) {
                totalRevenue += product.getPrice() * product.getQuantity(); 
                updateStatistics("src\\main\\java\\gui\\Statistics.txt",product.getName(),product.getQuantity(),product.getPrice(),product.getSize(),product.getImagePath(),product.getStock());
            }
            clear(); // Xóa giỏ hàng sau khi thanh toán
            JOptionPane.showMessageDialog(null, "Thanh toán thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Giỏ hàng trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    // Xóa sản phẩm khỏi giỏ hàng với xác nhận từ người dùng
    public void removeProduct(Product product) {
        int response = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sản phẩm này khỏi giỏ hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            products.remove(product);
            JOptionPane.showMessageDialog(null, "Sản phẩm đã được xóa khỏi giỏ hàng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Xóa tất cả sản phẩm trong giỏ hàng
    public void clear() {
        products.clear();
    }

    // Lấy danh sách sản phẩm trong giỏ hàng
    public List<Product> getProducts() {
        return products;
    }

    // Lấy danh sách lịch sử mua hàng
    public List<PurchaseHistory> getPurchaseHistories() {
        return new ArrayList<>(purchaseHistories); // Trả về bản sao của danh sách lịch sử mua hàng
    }

    // Lấy danh sách sản phẩm đã mua từ lịch sử mua hàng
    public List<Product> getPurchasedProducts() {
        List<Product> purchasedProducts = new ArrayList<>();
        for (PurchaseHistory history : purchaseHistories) {
            purchasedProducts.addAll(history.getPurchasedProducts());
        }
        return purchasedProducts; // Trả về danh sách tất cả sản phẩm đã mua
    }
    // Lấy tổng số sản phẩm đã bán
    public int getTotalProductsSold() {
        return purchaseHistories.stream()
                .mapToInt(history -> history.getPurchasedProducts().size())
                .sum(); // Tổng số sản phẩm đã bán
    }

    // Lấy tổng doanh thu
    public double getTotalRevenue() {
        return totalRevenue;
    }
    private void updateStatistics(String fileName,String name,int quantity,double price,String size,String anh,int stock){
        ArrayList<String> lines = new ArrayList<>();
        String s = name;
        boolean check=false;
        File accountFile = new File(new File("src\\main\\java\\gui\\Statistics.txt").getAbsolutePath());
        try{
            Scanner sc = new Scanner(accountFile);
            while(sc.hasNextLine()){
                String x = sc.nextLine();
                if(x.startsWith(s)) {
                    check=true;
                    String[]w = x.split("[' ']+");
                    x = w[0]+" "+w[1]+" "+Integer.toString(Integer.parseInt(w[2])+quantity)+" "+w[3]+" "+w[4]+" " +w[5];
                }
                lines.add(x);
            }
            sc.close();
        }catch(FileNotFoundException e){
        }
        if(!check){
            String x = name + " " + Double.toString(price)+" "+Integer.toString(quantity)+" "+size+" " + Integer.toString(stock)+" " + anh;
            lines.add(x);
        }
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
}

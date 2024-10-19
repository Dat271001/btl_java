package controller;

import model.Product;
import model.User;

import java.util.ArrayList;

public class ProductManager {
    private ArrayList<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    // Thêm sản phẩm vào danh sách và gán người bán cho sản phẩm
    public void addProduct(User seller, Product product) {
        product.setSeller(seller); // Gán người bán cho sản phẩm
        products.add(product);
    }

    // Lấy danh sách sản phẩm của người bán
    public ArrayList<Product> getProductsBySeller(User seller) {
        ArrayList<Product> sellerProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getSeller() != null && product.getSeller().getUsername().equals(seller.getUsername())) {
                sellerProducts.add(product);
            }
        }
        return sellerProducts;
    }

    // Lấy tất cả sản phẩm (nếu cần)
    public ArrayList<Product> getAllProducts() {
        return products;
    }
}

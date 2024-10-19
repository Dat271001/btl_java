
package gui;
import controller.Cart;
import controller.ProductManager;
import controller.UserManager;
import model.Product;
import model.User;
import model.PurchaseHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;
public class MainScreen extends javax.swing.JFrame {
    private User user;
    private UserManager userManager;
    private Cart cart;
    private ArrayList<Product> products;  // Danh sách sản phẩm
    private ProductManager productManager;

    private JLabel balanceLabel;
    private JTable productTable;
    private JTextField searchField;
    private DefaultTableModel tableModel; // Sử dụng DefaultTableModel để quản lý bảng

     public MainScreen(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        this.productManager = new ProductManager(); // Khởi tạo danh sách sản phẩm
        this.products = createProductList(); // Khởi tạo danh sách sản phẩm mẫu
        this.cart = new Cart();  // Khởi tạo giỏ hàng cho người dùng

        setTitle("Main Screen - Welcome " + user.getUsername());
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Thêm thanh menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // Tạo menu Cá nhân
        JMenu accountMenu = new JMenu("Cá nhân");
        JMenuItem accountItem = new JMenuItem("Thông tin cá nhân");
        accountItem.addActionListener(e -> {
            AccountScreen accountScreen = new AccountScreen(user, cart);
            accountScreen.setVisible(true);
        });
        accountMenu.add(accountItem);

        // Tạo menu Giỏ hàng
        JMenu cartMenu = new JMenu("Giỏ hàng");
        JMenuItem cartItem = new JMenuItem("Xem Giỏ hàng");
        cartItem.addActionListener(e -> {
            CartScreen cartScreen = new CartScreen(cart);
            cartScreen.setVisible(true);
        });
        cartMenu.add(cartItem);
        
        // Tạo menu Nạp tiền
        JMenu depositMenu = new JMenu("Nạp tiền");
        JMenuItem depositItem = new JMenuItem("Nạp tiền");
        depositItem.addActionListener(e -> {
            QRCodeScreen qrCodeScreen = new QRCodeScreen(user);
            qrCodeScreen.setVisible(true);
        });
        depositMenu.add(depositItem);

        // Tạo menu Lịch sử mua hàng
        JMenu purchaseHistoryMenu = new JMenu("Lịch sử mua hàng");
        JMenuItem purchaseHistoryItem = new JMenuItem("Xem Lịch sử mua hàng");
        purchaseHistoryItem.addActionListener(e -> {
            PurchaseHistoryScreen purchaseHistoryScreen = new PurchaseHistoryScreen(cart);
            purchaseHistoryScreen.setVisible(true);
        });
        purchaseHistoryMenu.add(purchaseHistoryItem);

        // Thêm menu vào menu bar
        menuBar.add(accountMenu);
        menuBar.add(cartMenu);
        menuBar.add(depositMenu);
        menuBar.add(purchaseHistoryMenu); // Thêm menu Lịch sử mua hàng
        setJMenuBar(menuBar);

        // Giao diện chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Hiển thị số dư
        balanceLabel = new JLabel("Balance: $" + user.getBalance());

        // Tạo thanh tìm kiếm
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Thêm balanceLabel và searchPanel vào một panel và đặt ở phía trên
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(balanceLabel);
        topPanel.add(searchPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Tạo bảng sản phẩm với DefaultTableModel
        String[] columnNames = {"Product Image", "Product Name", "Price", "Size", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        updateProductTable();  // Cập nhật bảng sản phẩm ban đầu

        JScrollPane scrollPane = new JScrollPane(productTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Các nút chức năng
        JPanel buttonPanel = new JPanel();
        JButton addToCartButton = new JButton("Add to Cart");
        JButton checkoutButton = new JButton("Checkout");
        
        // Thêm các nút sắp xếp
        JButton sortPriceButton1 = new JButton("Sort by Price ↑ ");//Them
        JButton sortPriceButton2 = new JButton("Sort by Price ↓ ");//Them
        JButton sortQuantityButton = new JButton("Sort by Quantity");
        JButton sellProductButton = new JButton("Sell Product");
        
        buttonPanel.add(sellProductButton);
        buttonPanel.add(addToCartButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(sortPriceButton1);//Them
        buttonPanel.add(sortPriceButton2);//Them
        buttonPanel.add(sortQuantityButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Thêm sự kiện tìm kiếm
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().toLowerCase();
            ArrayList<Product> filteredProducts = new ArrayList<>();

            for (Product product : products) {
                if (product.getName().toLowerCase().contains(keyword)) {
                    filteredProducts.add(product);
                }
            }

            updateProductTable(filteredProducts);  // Cập nhật bảng với sản phẩm tìm thấy
        });
        // Thêm sự kiện sắp xếp theo giá tăng dần
        sortPriceButton1.addActionListener(e -> {
            Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
            updateProductTable(products);  // Cập nhật bảng sau khi sắp xếp
        });
        //Thêm sự kiện sắp xếp theo giá giảm dần
        sortPriceButton2.addActionListener(e -> {
            Collections.sort(products, Comparator.comparingDouble(Product::Getprice));
            updateProductTable(products);  // Cập nhật bảng sau khi sắp xếp
        });
        // Thêm sự kiện sắp xếp theo số lượng
        sortQuantityButton.addActionListener(e -> {
            Collections.sort(products, Comparator.comparingInt(Product::getQuantity));
            updateProductTable(products);  // Cập nhật bảng sau khi sắp xếp
        });
        
        // Sự kiện khi nhấn nút "Sell Product"
        sellProductButton.addActionListener(e -> {
            String productName = JOptionPane.showInputDialog("Enter product name:");
            String size = JOptionPane.showInputDialog("Enter size:");
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter stock:"));
            String imagePath = JOptionPane.showInputDialog("Enter image path:");

            Product product = new Product(productName, price, quantity, size, stock, imagePath);
            productManager.addProduct(user, product);
            products.add(product);  // Thêm sản phẩm mới vào danh sách sản phẩm
            updateProductTable(products);  // Cập nhật bảng sản phẩm
            JOptionPane.showMessageDialog(null, "Product added for sale!");
        });

        // Thêm sự kiện cho giỏ hàng
        addToCartButton.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                String productName = (String) productTable.getValueAt(selectedRow, 1);
                double price = (double) productTable.getValueAt(selectedRow, 2);
                String size = (String) productTable.getValueAt(selectedRow, 3);
                int quantity = (int) productTable.getValueAt(selectedRow, 4);

// Thêm         // Kiểm tra số lượng có đủ không
                if (quantity > 0) {
                    // Nhập số lượng cần mua
                    int t = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
                    // Kiểm tra xem số lượng sản phẩm mua có hợp lệ hay không
                    while(t>quantity) {
                        JOptionPane.showInputDialog("The quantity of imported products is invalid.");
                        t = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
                    }
                    cart.addProduct(new Product(productName, price, t, size, quantity, null));  // Thêm sản phẩm vào giỏ hàng
                    JOptionPane.showMessageDialog(null, "Product added to cart!");
                    products.get(selectedRow).setQuantity(quantity - t);  // Giảm số lượng trong danh sách sản phẩm
                    updateProductTable(products);  // Cập nhật lại bảng sản phẩm
                } else {
                    JOptionPane.showMessageDialog(null, "Product is out of stock!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a product!");
            }
        });

        // Thêm sự kiện thanh toán
        checkoutButton.addActionListener(e -> {
            double totalAmount = cart.calculateTotal();
            if (totalAmount > 0) {
                if (user.deductBalance(totalAmount)) {
                    cart.checkout();  // Cập nhật lịch sử mua hàng
                    JOptionPane.showMessageDialog(null, "Checkout successful! Total: $" + totalAmount);
                    balanceLabel.setText("Balance: $" + user.getBalance());  // Cập nhật số dư
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough balance for checkout!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your cart is empty!");
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    private void updateProductTable() {
        tableModel.setRowCount(0);  // Xóa tất cả hàng trong bảng
        for (Product product : products) {
            tableModel.addRow(new Object[]{
                null,  // Có thể thêm hình ảnh ở đây nếu cần
                product.getName(),
                product.getPrice(),
                product.getSize(),
                product.getQuantity()
            });
        }
    }
    private void updateProductTable(ArrayList<Product> filteredProducts) {
        tableModel.setRowCount(0);  // Xóa tất cả hàng trong bảng
        for (Product product : filteredProducts) {
            tableModel.addRow(new Object[]{
                null,  // Có thể thêm hình ảnh ở đây nếu cần
                product.getName(),
                product.getPrice(),
                product.getSize(),
                product.getQuantity()
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<Product> createProductList() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product("T-shirt", 19.99, 10, "M", 10, "path/to/image1.jpg"));
        productList.add(new Product("Jeans", 39.99, 5, "L", 5, "path/to/image2.jpg"));
        productList.add(new Product("Sneakers", 59.99, 3, "42", 3, "path/to/image3.jpg"));
        return productList;
    }
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            User testUser = new User("testUser", "password", 100.00);
            UserManager userManager = new UserManager();
            new MainScreen(testUser, userManager);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

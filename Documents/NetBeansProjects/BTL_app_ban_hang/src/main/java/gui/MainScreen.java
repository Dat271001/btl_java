
package gui;
import controller.Cart;
import controller.ProductManager;
import controller.UserManager;
import duancuahang1.duancuahang;
import model.Product;
import model.User;
import model.PurchaseHistory;

import static duancuahang1.duancuahang.userManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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

    private Color lightOrange = new Color(248, 87, 55);
    private Color boldOrange = new Color(228, 67, 35);
    private Color lightGrey = new Color(235, 235, 235);
    private Color boldGrey = new Color(224,224,224);
    
     public MainScreen(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        this.productManager = new ProductManager(); // Khởi tạo danh sách sản phẩm
        this.products = createProductList(); // Khởi tạo danh sách sản phẩm mẫu
        this.cart = new Cart();  // Khởi tạo giỏ hàng cho người dùng
        this.setResizable(false);

        setTitle("Main Screen - Welcome " + user.getUsername());
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        this.getContentPane().setBackground(boldGrey);

        //1 - Thêm thanh menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        menuBar.setBorder(BorderFactory.createEmptyBorder());
        menuBar.setBackground(boldOrange);
        
        // Tạo menu Cá nhân
        JMenu accountMenu = new JMenu("Cá nhân");
        JMenuItem accountItem = new JMenuItem("Thông tin cá nhân");
        accountItem.addActionListener(e -> {
            AccountScreen accountScreen = new AccountScreen(user, cart,userManager);
            accountScreen.setVisible(true);
        });
        accountMenu.setForeground(Color.white);
        accountMenu.add(accountItem);

        // Tạo menu Giỏ hàng
//        JMenu cartMenu = new JMenu("Giỏ hàng");
//        JMenuItem cartItem = new JMenuItem("Xem Giỏ hàng");
//        cartItem.addActionListener(e -> {
//            CartScreen cartScreen = new CartScreen(cart);
//            cartScreen.setVisible(true);
//        });
//        cartMenu.add(cartItem);
        
        // Tạo menu Nạp tiền
        JMenu depositMenu = new JMenu("$: " + user.getBalance());
        JMenuItem depositItem = new JMenuItem("Nạp tiền");
        depositItem.addActionListener(e -> {
            QRCodeScreen qrCodeScreen = new QRCodeScreen(user);
            qrCodeScreen.setVisible(true);
        });
        depositMenu.setForeground(Color.white);
        depositMenu.add(depositItem);

        // Tạo menu Lịch sử mua hàng
        JMenu purchaseHistoryMenu = new JMenu("Lịch sử mua hàng");
        JMenuItem purchaseHistoryItem = new JMenuItem("Xem Lịch sử mua hàng");
        purchaseHistoryItem.addActionListener(e -> {
            PurchaseHistoryScreen purchaseHistoryScreen = new PurchaseHistoryScreen(cart);
            purchaseHistoryScreen.setVisible(true);
        });
        purchaseHistoryMenu.setForeground(Color.white);
        purchaseHistoryMenu.add(purchaseHistoryItem);

        // Thêm menu vào menu bar
        menuBar.add(accountMenu);
//        menuBar.add(cartMenu);
        menuBar.add(depositMenu);
        menuBar.add(purchaseHistoryMenu); // Thêm menu Lịch sử mua hàng
        setJMenuBar(menuBar);

        //2 - Giao diện chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setLocation(20, 150);
        mainPanel.setSize(940,450);
        

        // Hiển thị số dư
//        balanceLabel = new JLabel("Balance: $" + user.getBalance());


        //3 - Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setVisible(true);
        searchPanel.setSize(1200,90);
        searchPanel.setLocation(0, 0);
        searchPanel.setBackground(lightOrange);
        
        //Home Button
        JButton homePage = new JButton();
//        homePage.setVisible(true);
        homePage.setSize(100, 80);
        homePage.setLocation(50, 5);
        ImageIcon logo = new ImageIcon(new File("src\\main\\java\\img\\icons8-home-screen-100.png").getAbsolutePath());
        homePage.setIcon(new controller.NoScalingIcon( logo ));
        homePage.setBorder(BorderFactory.createEmptyBorder());
        homePage.setBackground(lightOrange);
        searchPanel.add(homePage);
        
        //Search bar
        JTextField searchField = new JTextField(15);
//        searchField.setVisible(true);
        searchField.setSize(700, 40);
        searchField.setLocation(200, 20);
        searchPanel.add(searchField);
        
        //Search button
        JButton searchButton = new JButton();
//        searchButton.setVisible(true);
        searchButton.setSize(70, 40);
        searchButton.setLocation(920, 20);
        searchButton.setBackground(Color.white);
        searchButton.setBorder(BorderFactory.createEmptyBorder());
        searchButton.setIcon( new controller.NoScalingIcon( new ImageIcon(new File("src\\main\\java\\img\\icons8-search-24.png").getAbsolutePath()) ) );
        searchPanel.add(searchButton);
        
        JButton cartButton = new JButton();
//        cartButton.setVisible(true);
        cartButton.setSize(100, 60);
        cartButton.setLocation(1040, 10);
        cartButton.setBackground(lightOrange);
        cartButton.setIcon( new controller.NoScalingIcon( new ImageIcon(new File("src\\main\\java\\img\\icons8-shopping-cart-50.png").getAbsolutePath()) ) );
        cartButton.addActionListener(e -> {
            CartScreen cartScreen = new CartScreen(cart);
            cartScreen.setVisible(true);
        });
        searchPanel.add(cartButton);
        
        // Thêm balanceLabel và searchPanel vào một panel và đặt ở phía trên
//        JPanel topPanel = new JPanel(new GridLayout(2, 1));
//        topPanel.add(balanceLabel);
//        topPanel.add(searchPanel);
//        mainPanel.add(topPanel, BorderLayout.NORTH);
        this.add(searchPanel);
//        mainPanel.add(searchPanel);
        
        
        // Tạo bảng sản phẩm với DefaultTableModel
        String[] columnNames = {"Product Image", "Product Name", "Price", "Size", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0){
        @Override
          public boolean isCellEditable(int row, int column) {
        // Trả về false để không cho phép sửa ô nào trong bảng
        return false;
        }
        };
        productTable = new JTable(tableModel);
        productTable.setRowHeight(40);
        productTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTableHeader header = productTable.getTableHeader();
//        header.setFont(new Font("Segoe UI", Font.BOLD, 14);
        productTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
//        productTable.setHeFont(new Font("Segoe UI", Font.PLAIN, 14));
//        productTable.setLayout(new FlowLayout());
        updateProductTable();  // Cập nhật bảng sản phẩm ban đầu

        JScrollPane scrollPane = new JScrollPane(productTable);
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 4 - FunctionButtonsPanel - Các nút chức năng
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLocation(980, 150);
        buttonPanel.setSize(200,450);
        buttonPanel.setLayout(null);
        
        // 2 Nut chuc nhung lon
        JButton addToCartButton = new JButton("Add to Cart");
        JButton checkoutButton = new JButton("Checkout");
        
        // Thêm các nút sắp xếp
        JButton sortPriceButton1 = new JButton("Sort by Price ↑ ");//Them
        JButton sortPriceButton2 = new JButton("Sort by Price ↓ ");//Them
        JButton sortQuantityButton = new JButton("Sort by Quantity");
        JButton sellProductButton = new JButton("Sell Product");
        
        sortPriceButton1.setSize(160,40);
        sortPriceButton1.setLocation(20, 20);
        sortPriceButton1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sortPriceButton1.setBackground(lightOrange);
        sortPriceButton1.setForeground(Color.white);
//        sortPriceButton1.setBorder(BorderFactory.createEmptyBorder());
        
        sortPriceButton2.setSize(160,40);
        sortPriceButton2.setLocation(20, 70);
        sortPriceButton2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sortPriceButton2.setBackground(lightOrange);
        sortPriceButton2.setForeground(Color.white);
//        sortPriceButton2.setBorder(BorderFactory.createEmptyBorder());
        
        sortQuantityButton.setSize(160,40);
        sortQuantityButton.setLocation(20, 120);
        sortQuantityButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sortQuantityButton.setBackground(lightOrange);
        sortQuantityButton.setForeground(Color.white);
//        sortQuantityButton.setBorder(BorderFactory.createEmptyBorder());
        
        sellProductButton.setSize(160,40);
        sellProductButton.setLocation(20, 170);
        sellProductButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sellProductButton.setBackground(lightOrange);
        sellProductButton.setForeground(Color.white);
//        sellProductButton.setBorder(BorderFactory.createEmptyBorder());
 if (userManager.AdminCheck(user.getUsername(), user.getPassword())) {
            sellProductButton.setVisible(true); // Hiện nút nếu là admin
        } else {
            sellProductButton.setVisible(false); // Ẩn nút nếu không phải admin
        }

  
        addToCartButton.setSize(180,70);
        addToCartButton.setLocation(10, 280);
        addToCartButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        addToCartButton.setBackground(lightOrange);
        addToCartButton.setForeground(Color.white);
//        addToCartButton.setBorder(BorderFactory.createEmptyBorder());
        
        checkoutButton.setSize(180,70);
        checkoutButton.setLocation(10, 370);
        checkoutButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        checkoutButton.setBackground(lightOrange);
        checkoutButton.setForeground(Color.white);
//        checkoutButton.setBorder(BorderFactory.createEmptyBorder());
        
        buttonPanel.add(sellProductButton);
        buttonPanel.add(addToCartButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(sortPriceButton1);//Them
        buttonPanel.add(sortPriceButton2);//Them
        buttonPanel.add(sortQuantityButton);
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        this.add(buttonPanel);
        
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
                    depositMenu.setText("$: " + user.getBalance());  // Cập nhật số dư
                    updateBalanceToFile(user.getUsername(),user.getPassword(),user.getBalance(), "src\\main\\java\\controller\\Accs.txt",totalAmount);
                    for(Product product: products){
                        updateProduct("src\\main\\java\\gui\\Product.txt",product.getQuantity(),product.getName());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough balance for checkout!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your cart is empty!");
            }
        });

        this.add(mainPanel);
        setVisible(true);
    }
    private void updateProduct(String fileName,int quantity,String name){
        ArrayList<String> lines = new ArrayList<>();
        String s = name;
        File accountFile = new File(new File("src\\main\\java\\gui\\Product.txt").getAbsolutePath());
        try{
            Scanner sc = new Scanner(accountFile);
            while(sc.hasNextLine()){
                String x = sc.nextLine();
                if(x.startsWith(s)) {
                    String[]w = x.split("[' ']+");
                    x = w[0]+" "+w[1]+" "+Integer.toString(quantity)+" "+w[3]+" "+w[4]+" " +w[5];
                }
                lines.add(x);
            }
            sc.close();
        }catch(FileNotFoundException e){
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
    private void updateBalanceToFile(String name,String pass,double newBalance, String fileName,double total) {
        ArrayList<String> lines = new ArrayList<>();
        String s = name +" "+pass;
        File accountFile = new File(new File("src\\main\\java\\controller\\Accs.txt").getAbsolutePath());
        try{
            Scanner sc = new Scanner(accountFile);
            while(sc.hasNextLine()){
                String x = sc.nextLine();
                if(x.startsWith("Admin 1234")) {
                    String[]w = x.split("[' ']+");
                    x = w[0]+" "+w[1]+" "+Double.toString(Double.parseDouble(w[2])+total);
                }
                if(x.startsWith(s)) x= s + " "+Double.toString(newBalance);
                lines.add(x);
            }
            sc.close();
        }catch(FileNotFoundException e){
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
        File accountFile = new File(new File("src\\main\\java\\gui\\Product.txt").getAbsolutePath());
        try{
            Scanner sc = new Scanner(accountFile);
            while(sc.hasNextLine()){
                String s = sc.nextLine();
                String[]w = s.split("[' ']+");
                productList.add(new Product(w[0],Double.parseDouble(w[1]),Integer.parseInt(w[2]), w[3],Integer.parseInt(w[4]),w[5]));
            }
            sc.close();
        }catch(FileNotFoundException e){
        }
        return productList;
    }
//     public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            User testUser = new User("testUser", "password", 100.00);
////            UserManager userManager = new UserManager();
//            duancuahang.mainScreen = new MainScreen(testUser, userManager);
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}


package gui;
import controller.Cart;
import controller.ProductManager;
import controller.UserManager;
import duancuahang1.duancuahang;
import model.Product;
import model.*;
import model.PurchaseHistory;

import static duancuahang1.duancuahang.userManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
public class MainScreenAdmin extends javax.swing.JFrame {
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
    
     public MainScreenAdmin(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        this.productManager = new ProductManager(); // Khởi tạo danh sách sản phẩm
        this.products = createProductList(); // Khởi tạo danh sách sản phẩm mẫu
        this.cart = new Cart();  // Khởi tạo giỏ hàng cho người dùng
        this.setResizable(false);

        setTitle(" Welcome Admin ");
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
        // Tạo menu Nạp tiền
        JMenu depositMenu = new JMenu("Số dư hiện tại: " + user.getBalance()+"$");
//        JMenuItem depositItem = new JMenuItem("Nạp tiền");
//        depositItem.addActionListener(e -> {
//            QRCodeScreen qrCodeScreen = new QRCodeScreen(user);
//            qrCodeScreen.setVisible(true);
//        });
        depositMenu.setForeground(Color.white);
//        depositMenu.add(depositItem);

         // Tạo menu thống kê
        JMenu purchaseHistoryMenu = new JMenu("Thống kê");
        JMenuItem HistoryItem = new JMenuItem("Xem số sản phẩm đã bán");
        HistoryItem.addActionListener(e -> {
           
           File accountFile = new File(new File("src\\main\\java\\gui\\Statistics.txt").getAbsolutePath());
           try{
            Scanner sc = new Scanner(accountFile);
            while(sc.hasNextLine()){
               String s = sc.nextLine();
               String[] w = s.split("[' ']+");
               cart.AddProduct(new Product(w[0],Double.parseDouble(w[1]),Integer.parseInt(w[2]), w[3],Integer.parseInt(w[4]),w[5]));
            }
            sc.close();
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }
            StatisticsScreen purchaseHistoryScreen = new StatisticsScreen(cart);
            purchaseHistoryScreen.setVisible(true);
        });
        purchaseHistoryMenu.setForeground(Color.white);
        purchaseHistoryMenu.add(HistoryItem);

        // Thêm menu vào menu bar
        menuBar.add(accountMenu);
//        menuBar.add(cartMenu);
        menuBar.add(depositMenu);
        menuBar.add(purchaseHistoryMenu); // Thêm menu Sản phẩm đã bán
        setJMenuBar(menuBar);

        //2 - Giao diện chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setLocation(20, 150);
        mainPanel.setSize(940,450);
        

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
        
        this.add(searchPanel);
        
        // Tạo bảng sản phẩm với DefaultTableModel
        String[] columnNames = {"Product Image", "Product Name", "Price", "Size", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel){
        @Override
          public boolean isCellEditable(int row, int column) {
        // Trả về false để không cho phép sửa ô nào trong bảng
        return false;
        }
        };
        productTable.setRowHeight(40);
        productTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTableHeader header = productTable.getTableHeader();
        productTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateProductTable();  // Cập nhật bảng sản phẩm ban đầu

        JScrollPane scrollPane = new JScrollPane(productTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 4 - FunctionButtonsPanel - Các nút chức năng
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLocation(980, 150);
        buttonPanel.setSize(200,450);
        buttonPanel.setLayout(null);
       
        // Thêm các nút sắp xếp
        JButton sortPriceButton1 = new JButton("Sort by Price ↑ ");
        JButton sortPriceButton2 = new JButton("Sort by Price ↓ ");
        JButton sortQuantityButton = new JButton("Sort by Quantity");
        JButton sellProductButton = new JButton("Sell Product");
        
        sortPriceButton1.setSize(160,40);
        sortPriceButton1.setLocation(20, 20);
        sortPriceButton1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sortPriceButton1.setBackground(lightOrange);
        sortPriceButton1.setForeground(Color.white);
        
        sortPriceButton2.setSize(160,40);
        sortPriceButton2.setLocation(20, 70);
        sortPriceButton2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sortPriceButton2.setBackground(lightOrange);
        sortPriceButton2.setForeground(Color.white);
        
        sortQuantityButton.setSize(160,40);
        sortQuantityButton.setLocation(20, 120);
        sortQuantityButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sortQuantityButton.setBackground(lightOrange);
        sortQuantityButton.setForeground(Color.white);
        
        sellProductButton.setSize(160,40);
        sellProductButton.setLocation(20, 170);
        sellProductButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sellProductButton.setBackground(lightOrange);
        sellProductButton.setForeground(Color.white);
        
      
        buttonPanel.add(sellProductButton);
        buttonPanel.add(sortPriceButton1);
        buttonPanel.add(sortPriceButton2);
        buttonPanel.add(sortQuantityButton);

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
            try (PrintWriter printWriter = new PrintWriter(new FileWriter("src\\main\\java\\gui\\Product.txt", true))) {
                 String s = productName+" "+Double.toString(price)+" "+ Integer.toString(quantity)+" "+ size +" "+ Integer.toString(stock)+" "+ imagePath;
                 printWriter.println(s);
            }catch(IOException ex){
                
            }
            Product product = new Product(productName, price, quantity, size, stock, imagePath);
            productManager.addProduct(user, product);
            products.add(product);  // Thêm sản phẩm mới vào danh sách sản phẩm
            updateProductTable(products);  // Cập nhật bảng sản phẩm
            JOptionPane.showMessageDialog(null, "Product added for sale!");
        });
       
        this.add(mainPanel);
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
}


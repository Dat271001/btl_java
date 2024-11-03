
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        setSize(1500,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        this.getContentPane().setBackground(Color.WHITE);

        
        //1- left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setSize(300,800);
        leftPanel.setLocation(0,0);
        leftPanel.setBackground(Color.blue);
        
        JLabel homePage = new JLabel();
        homePage.setSize(300,200);
        homePage.setLocation(0,0);
        leftPanel.add(homePage);
        
        JButton accountItem = new JButton("Profile");
        accountItem.setSize(300,50);
        accountItem.setLocation(0,250);
        leftPanel.add(accountItem);
        
        JButton purchaseHistoryItem = new JButton("History");
        purchaseHistoryItem.setSize(300,50);
        purchaseHistoryItem.setLocation(0,330);
        leftPanel.add(purchaseHistoryItem);
        
        JButton depositItem = new JButton("Deposit$");
        depositItem.setSize(300,50);
        depositItem.setLocation(0,410);
        leftPanel.add(depositItem);
        
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setSize(300,50);
        checkoutButton.setLocation(0,490);
        leftPanel.add(checkoutButton);
        
        JButton cartButton = new JButton("Cart");
        cartButton.setSize(300,50);
        cartButton.setLocation(0,570);
        leftPanel.add(cartButton);
        
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setSize(300,50);
        logoutButton.setLocation(0,650);
        leftPanel.add(logoutButton);
        
        this.add(leftPanel);
        
        //2- search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 5));
        searchPanel.setSize(1500,50);
        searchPanel.setLocation(300,250);
        searchPanel.setBackground(Color.ORANGE);
        
        JLabel searchTitle = new JLabel("Search: ");
        searchPanel.add(searchTitle);
        
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(700,40));
        searchPanel.add(searchField);
        
        this.add(searchPanel);
        
//        3- info Panel & Image
        JLabel productImage = new JLabel("IMG");
        productImage.setLocation(350,50);
        productImage.setSize(200,200);
        this.add(productImage);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setSize(900,250);
        infoPanel.setLocation(600,0);
        infoPanel.setBackground(Color.yellow);
        
        JLabel prodName = new JLabel("Name:");
        prodName.setLocation(100,50);
        prodName.setSize(100,20);
        infoPanel.add(prodName);
        
        JTextField prodNameField = new JTextField("Name");
        prodNameField.setLocation(200,50);
        prodNameField.setSize(200,20);
        infoPanel.add(prodNameField);
        
        JLabel prodSize = new JLabel("Size:");
        prodSize.setLocation(100,100);
        prodSize.setSize(100,20);
        infoPanel.add(prodSize);
        
        JTextField prodSizeField = new JTextField("Name");
        prodSizeField.setLocation(200,100);
        prodSizeField.setSize(200,20);
        infoPanel.add(prodSizeField);
        
        JLabel prodPrice = new JLabel("Price:");
        prodPrice.setLocation(100,150);
        prodPrice.setSize(100,20);
        infoPanel.add(prodPrice);
        
        JTextField prodPriceField = new JTextField("Name");
        prodPriceField.setLocation(200,150);
        prodPriceField.setSize(200,20);
        infoPanel.add(prodPriceField);
        
        JLabel prodQuantity = new JLabel("Quantity:");
        prodQuantity.setLocation(100,200);
        prodQuantity.setSize(100,20);
        infoPanel.add(prodQuantity);
        
        JTextField prodQuantityField = new JTextField("Name");
        prodQuantityField.setLocation(200,200);
        prodQuantityField.setSize(200,20);
        infoPanel.add(prodQuantityField);
        
        
        
        this.add(infoPanel);
        
        JPanel functionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 5));
        functionPanel.setSize(1500,50);
        functionPanel.setLocation(300,300);
        functionPanel.setBackground(Color.cyan);
        
        JButton sortPriceButton1 = new JButton("Sort UP");
        functionPanel.add(sortPriceButton1);
        
        JButton sortPriceButton2 = new JButton("Sort DOWN");
        functionPanel.add(sortPriceButton2);
        
        JButton sortQuantityButton = new JButton("Sort by quantity");
        functionPanel.add(sortQuantityButton);
        
        JButton searchButton = new JButton("Search");
        functionPanel.add(searchButton);
        
        JButton addToCartButton = new JButton("Add to cart");
        functionPanel.add(addToCartButton);
        
        this.add(functionPanel);
        
        //4- Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setSize(1200,450);
        mainPanel.setLocation(300,350);
        mainPanel.setBackground(Color.red);
        
        
        accountItem.addActionListener(e -> {
            AccountScreen accountScreen = new AccountScreen(user, cart,userManager);
            accountScreen.setVisible(true);
        });
        
        // Tạo menu Giỏ hàng
//        JMenu cartMenu = new JMenu("Giỏ hàng");
//        JMenuItem cartItem = new JMenuItem("Xem Giỏ hàng");
//        cartItem.addActionListener(e -> {
//            CartScreen cartScreen = new CartScreen(cart);
//            cartScreen.setVisible(true);
//        });
//        cartMenu.add(cartItem);
        
        // Tạo menu Nạp tiền
//Tam thoi cmt        JMenu depositMenu = new JMenu("$: " + user.getBalance());
        depositItem.addActionListener(e -> {
            QRCodeScreen qrCodeScreen = new QRCodeScreen(user);
            qrCodeScreen.setVisible(true);
            
            // Khi QRCodeScreen đóng, cập nhật lại text của depositMenu
// Tam thoi cmt            depositMenu.setText("$: " + user.getBalance());
        });
        
        
        // Tạo menu Lịch sử mua hàng
        purchaseHistoryItem.addActionListener(e -> {
            PurchaseHistoryScreen purchaseHistoryScreen = new PurchaseHistoryScreen(cart);
            purchaseHistoryScreen.setVisible(true);
        });


        // Hiển thị số dư
//        balanceLabel = new JLabel("Balance: $" + user.getBalance());


        cartButton.addActionListener(e -> {
            CartScreen cartScreen = new CartScreen(cart);
            cartScreen.setVisible(true);
        });
        
        
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
        this.add(mainPanel);

// if (userManager.AdminCheck(user.getUsername(), user.getPassword())) {
//            sellProductButton.setVisible(true); // Hiện nút nếu là admin
//        } else {
//            sellProductButton.setVisible(false); // Ẩn nút nếu không phải admin
//        }

  
        
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
            updateProductTable(products);  
        });
        //Thêm sự kiện sắp xếp theo giá giảm dần
        sortPriceButton2.addActionListener(e -> {
            Collections.sort(products, Comparator.comparingDouble(Product::Getprice));
            updateProductTable(products);  
        });
        // Thêm sự kiện sắp xếp theo số lượng
        sortQuantityButton.addActionListener(e -> {
            Collections.sort(products, Comparator.comparingInt(Product::getQuantity));
            updateProductTable(products);
        });
        // Thêm sự kiện cho giỏ hàng
        addToCartButton.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                String productName = (String) productTable.getValueAt(selectedRow, 1);
                double price = (double) productTable.getValueAt(selectedRow, 2);
                String size = (String) productTable.getValueAt(selectedRow, 3);
                int quantity = (int) productTable.getValueAt(selectedRow, 4);

           // Kiểm tra số lượng có đủ không
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
                    products.get(selectedRow).setQuantity_Sold(t);  
                    updateProductTable(products);  
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
//Tam thoi cmt                    depositMenu.setText("$: " + user.getBalance());  // Cập nhật số dư
                    updateBalanceToFile(user.getUsername(),user.getPassword(),user.getBalance(), "src\\main\\java\\controller\\Accs.txt",totalAmount);
                    for(Product product: products){
                        updateProduct("src\\main\\java\\gui\\Product.txt",product.getQuantity()-product.getQuantity_Sold(),product.getName());
                    }
                    products = new ArrayList<>();
                    File accountFile = new File(new File("src\\main\\java\\gui\\Product.txt").getAbsolutePath());
                    try{
                        Scanner sc = new Scanner(accountFile);
                        while(sc.hasNextLine()){
                            String s = sc.nextLine();
                            String[]w = s.split("[' ']+");
                            products.add(new Product(w[0],Double.parseDouble(w[1]),Integer.parseInt(w[2]), w[3],Integer.parseInt(w[4]),w[5]));
                            }
                        sc.close();
                    }catch(FileNotFoundException ex){
                    }
                    updateProductTable(products);
                }else {
                    JOptionPane.showMessageDialog(null, "Not enough balance for checkout!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your cart is empty!");
            }
        });

        this.add(mainPanel);
        setVisible(true);
        
        //Listener for List
        // Get the selection model and set it to single selection mode
        ListSelectionModel selectionModel = productTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add a ListSelectionListener to handle row selection changes
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Ignore extra events while selection is adjusting
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) { // Ensure a row is selected
                        String imgPath = (String) productTable.getValueAt(selectedRow, 0);
                        ImageIcon originalIcon = new ImageIcon(imgPath);
                        Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                        ImageIcon resizedIcon = new ImageIcon(scaledImage);
                        
                        productImage.setIcon(resizedIcon);
                        prodNameField.setText( (String) productTable.getValueAt(selectedRow, 1));
                        prodPriceField.setText("" + (double) productTable.getValueAt(selectedRow, 2) );
                        prodSizeField.setText((String) productTable.getValueAt(selectedRow, 3));
                        prodQuantityField.setText( "" + (int) productTable.getValueAt(selectedRow, 4));
                    }
                }
            }
        });
        
        //END
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
//                null,  // Có thể thêm hình ảnh ở đây nếu cần
                product.getImagePath(),
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
//                null,  // Có thể thêm hình ảnh ở đây nếu cần
                product.getImagePath(),
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

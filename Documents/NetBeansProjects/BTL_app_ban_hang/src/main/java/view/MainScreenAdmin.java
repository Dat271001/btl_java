
package view;
import controller.Cart;
import controller.ProductManager;
import controller.UserManager;
import controller.Main;
import model.Product;
import model.*;
import model.PurchaseHistory;

import static controller.Main.userManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private DefaultTableModel tableModel; // Sử dụng DefaultTableModel để quản lý bảng
    public static JLabel depositMenu;
    
    JPanel leftPanel, searchPanel, infoPanel, mainPanel, functionPanel;
    JLabel homePage;
    JButton accountItem, HistoryItem, depositItem, sellProductButton, logoutButton;
    JLabel searchTitle;
    JTextField searchField;
    JButton searchButton;
    JLabel productImage, prodName, prodSize, prodPrice, prodQuantity;
    JTextField prodNameField, prodSizeField, prodPriceField, prodQuantityField;
    JButton sortPriceButton1, sortPriceButton2, sortQuantityButton, adjustmentButton;
    Color cBlack = new Color(39, 35, 67);
    
    Product selectedProd = null;
    
    
    private void UI(){
        this.setResizable(false);

        setTitle("Main Screen - Welcome " + user.getUsername());
        setSize(1500,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        leftPanelUI();
        searchPanelUI();
        infoPanelUI();
        functionPanelUI();
        mainPanelUI();
    }
    
    private JButton leftPanelCustomButton(String text, int x, int y, JButton button, String path){
        button = new JButton(text);
        button.setSize(300,60);
        button.setLocation(x, y);
        button.setBackground(cBlack);
        button.setForeground(Color.WHITE);
        button.setBorder(new EmptyBorder(5,5,5,5));
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        ImageIcon originalIcon = new ImageIcon(new File("src\\main\\java\\img\\" + path).getAbsolutePath());
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        button.setIcon(resizedIcon);
        return button;
    }
    
    private void leftPanelUI(){
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setSize(300,800);
        leftPanel.setLocation(0,0);
        leftPanel.setBackground(cBlack);
        
        homePage = new JLabel();
        homePage.setSize(300,200);
        homePage.setLocation(0,0);
        ImageIcon originalIcon = new ImageIcon(new File("src\\main\\java\\img\\MAIN.png").getAbsolutePath());
        Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        homePage.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePage.setHorizontalAlignment(SwingConstants.CENTER);
        homePage.setIcon(resizedIcon);
        leftPanel.add(homePage);
        
        depositMenu = new JLabel("$: " + user.getBalance());
        depositMenu.setLocation(0,250);
        depositMenu.setSize(300,50);
        depositMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
        depositMenu.setForeground(Color.WHITE);
         originalIcon = new ImageIcon(new File("src\\main\\java\\img\\Cash.jpg").getAbsolutePath());
         scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
         resizedIcon = new ImageIcon(scaledImage);
        depositMenu.setIcon(resizedIcon);
        depositMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositMenu.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(depositMenu);
        
        accountItem = leftPanelCustomButton("Profile", 0, 300, accountItem, "ProfileIcon.jpg");
        leftPanel.add(accountItem);
        
        HistoryItem = leftPanelCustomButton("History", 0, 370, HistoryItem, "History.jpg");
        leftPanel.add(HistoryItem);
        
        depositItem = leftPanelCustomButton("Withdraw $", 0, 440, depositItem, "Cash.jpg");
        leftPanel.add(depositItem);
        
        sellProductButton = leftPanelCustomButton("Sell Product", 0, 510, sellProductButton, "Cart.jpg");
        leftPanel.add(sellProductButton);
        
        logoutButton = leftPanelCustomButton("Log Out", 0, 700, logoutButton, "Logout.jpg");
        leftPanel.add(logoutButton);
        
        this.add(leftPanel);
    }
    
    private void searchPanelUI(){
         searchPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 100, 5));
        searchPanel.setSize(1500,50);
        searchPanel.setLocation(300,250);
        searchPanel.setBackground(new Color(186, 232, 232));
        
        searchTitle = new JLabel("Search: ");
        searchTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        searchTitle.setForeground(Color.white);
        searchPanel.add(searchTitle);
        
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(600,40));
        searchPanel.add(searchField);
        
        searchButton = new JButton("Search");
        searchPanel.add(searchButton);
        
        this.add(searchPanel);
    }
    
    private void infoPanelUI(){
             productImage = new JLabel();
        productImage.setLocation(325,25);
        productImage.setSize(250,250);
        this.add(productImage);
        
        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setSize(900,250);
        infoPanel.setLocation(600,0);
        
        prodName = new JLabel("Name:");
        prodName.setFont(new Font("Segoe UI", Font.BOLD, 16));
        prodName.setLocation(150,50);
        prodName.setSize(100,20);
        infoPanel.add(prodName);
        
        prodNameField = new JTextField("Name");
        prodNameField.setLocation(250,50);
        prodNameField.setSize(200,25);
        infoPanel.add(prodNameField);
        
        prodSize = new JLabel("Size:");
        prodSize.setFont(new Font("Segoe UI", Font.BOLD, 16));
        prodSize.setLocation(150,100);
        prodSize.setSize(100,20);
        infoPanel.add(prodSize);
        
        prodSizeField = new JTextField("Name");
        prodSizeField.setLocation(250,100);
        prodSizeField.setSize(200,25);
        infoPanel.add(prodSizeField);
        
        prodPrice = new JLabel("Price:");
        prodPrice.setFont(new Font("Segoe UI", Font.BOLD, 16));
        prodPrice.setLocation(150,150);
        prodPrice.setSize(100,20);
        infoPanel.add(prodPrice);
        
        prodPriceField = new JTextField("Name");
        prodPriceField.setLocation(250,150);
        prodPriceField.setSize(200,25);
        infoPanel.add(prodPriceField);
        
        prodQuantity = new JLabel("Quantity:");
        prodQuantity.setFont(new Font("Segoe UI", Font.BOLD, 16));
        prodQuantity.setLocation(150,200);
        prodQuantity.setSize(100,20);
        infoPanel.add(prodQuantity);
        
        prodQuantityField = new JTextField("Name");
        prodQuantityField.setLocation(250,200);
        prodQuantityField.setSize(200,25);
        infoPanel.add(prodQuantityField);
        
        
        
        this.add(infoPanel);
    }
    
    private void functionPanelUI(){
        functionPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 150, 5));
        functionPanel.setSize(1500,50);
        functionPanel.setLocation(300,300);
        functionPanel.setBackground(cBlack);
        functionPanel.setBackground(new Color(186, 232, 232));
        
        sortPriceButton1 = new JButton("Sort up");
        functionPanel.add(sortPriceButton1);
        
        sortPriceButton2 = new JButton("Sort down");
        functionPanel.add(sortPriceButton2);
        
        sortQuantityButton = new JButton("Sort by quantity");
        functionPanel.add(sortQuantityButton);
        
        adjustmentButton = new JButton("Adjust");
        functionPanel.add(adjustmentButton);
        
        this.add(functionPanel);
    }
    
    private void mainPanelUI(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setSize(1200,450);
        mainPanel.setLocation(300,350);
        mainPanel.setBackground(Color.red);
        
        // Tạo bảng sản phẩm với DefaultTableModel
        String[] columnNames = {"Product Name", "Price", "Size", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };
        productTable = new JTable(tableModel);
        productTable.setRowHeight(40);
        productTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTableHeader header = productTable.getTableHeader();
        productTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateProductTable();  // Cập nhật bảng sản phẩm ban đầu

        JScrollPane scrollPane = new JScrollPane(productTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(mainPanel);
    }
    
     public MainScreenAdmin(User user, UserManager userManager) {
        this.user = user;
        this.userManager = userManager;
        this.productManager = new ProductManager(); // Khởi tạo danh sách sản phẩm
        this.products = createProductList(); // Khởi tạo danh sách sản phẩm mẫu
        this.cart = new Cart();  // Khởi tạo giỏ hàng cho người dùng
        
        UI();
        
        accountItem.addActionListener(e -> {
            AccountScreen accountScreen = new AccountScreen(user, cart,userManager);
            accountScreen.setVisible(true);
        });
         //Tạo menu Nạp tiền
        depositItem.addActionListener(e -> {
            Withdraw withdraw = new Withdraw(user);
            withdraw.setVisible(true);
            
        //  Khi QRCodeScreen đóng, cập nhật lại text của depositMenu
            depositMenu.setText("$: " + user.getBalance());
        });

        HistoryItem.addActionListener(e -> {
           
           File accountFile = new File(new File("src\\main\\java\\files\\Statistics.txt").getAbsolutePath());
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
            try (PrintWriter printWriter = new PrintWriter(new FileWriter("src\\main\\java\\files\\Product.txt", true))) {
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
       
        adjustmentButton.addActionListener(e ->{
            //Xoa prod cu
            String imgPath = "";
            for (Product product : products){
                if(product.getName().equals(selectedProd.getName())){
                    imgPath = product.getImagePath();
                    products.remove(product);
                    break;
                }
            }
            System.out.println("After RM: " + products.size());
            
            //Them product moi
            String name = prodNameField.getText();
            double price = Double.parseDouble(prodPriceField.getText());
            String size = prodSizeField.getText();
            int quantity = Integer.parseInt(prodQuantityField.getText());
            Product TMPproduct = new Product(name, price, quantity, size, quantity, imgPath);
            productManager.addProduct(user, TMPproduct);
            products.add(TMPproduct);  // Thêm sản phẩm mới vào danh sách sản phẩm
            updateProductTable(products);
            System.out.println("After ADD: " + products.size());
            
            //Ghi de file
            try {
                File dataFile = new File( new File("src\\main\\java\\files\\Product.txt").getAbsolutePath());
                FileWriter writer = new FileWriter(dataFile);
                
                for (Product product : products){
                    String imagePath = product.getImagePath();
                    String[] tmp = imagePath.split("\\\\");
                    imagePath = tmp[tmp.length-1];
                    String s = product.getName()+" "+Double.toString(product.getPrice())+" "+ Integer.toString(product.getQuantity())+" "+ product.getSize() +" "+ Integer.toString(product.getQuantity())+" "+ imagePath;
                    writer.write(String.format("\n%s", s));
                }
                
                writer.close();
                
                
            } catch (IOException ex) {
            }
            System.out.println("After ALL: " + products.size());
            
        });

        ListSelectionModel selectionModel = productTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
              
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) { 
                        String pName = (String) productTable.getValueAt(selectedRow, 0);
                        
                        for (Product product : products){
                            if(product.getName().equals(pName)){
                                selectedProd = product;
                                prodNameField.setText(pName);
                                String imgPath = product.getImagePath();
                                ImageIcon originalIcon = new ImageIcon(imgPath);
                                Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                                ImageIcon resizedIcon = new ImageIcon(scaledImage);

                                productImage.setIcon(resizedIcon);
                                prodPriceField.setText("" + (double) productTable.getValueAt(selectedRow, 1) );
                                prodSizeField.setText((String) productTable.getValueAt(selectedRow, 2));
                                prodQuantityField.setText( "" + (int) productTable.getValueAt(selectedRow, 3));
                                break;
                            }
                        }
                    }
                }
            }
        });
        
        //Logout
        logoutButton.addActionListener(e->{
            System.out.println("LOGOUT");
            this.dispose();
            Main.login = new LoginForm();
            
        });
        
        this.add(mainPanel);
        setVisible(true);
    }

    private void updateProductTable() {
        tableModel.setRowCount(0);  // Xóa tất cả hàng trong bảng
        for (Product product : products) {
            tableModel.addRow(new Object[]{
//                product.getImagePath(),  // Có thể thêm hình ảnh ở đây nếu cần
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
//                product.getImagePath(),  // Có thể thêm hình ảnh ở đây nếu cần
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
        File accountFile = new File(new File("src\\main\\java\\files\\Product.txt").getAbsolutePath());
        try{
            Scanner sc = new Scanner(accountFile);
            while(sc.hasNextLine()){
                String s = sc.nextLine();
                if(s.isBlank() || s.isEmpty()) continue;
                String[]w = s.split("[' ']+");
                productList.add(new Product(w[0],Double.parseDouble(w[1]),Integer.parseInt(w[2]), w[3],Integer.parseInt(w[4]),w[5]));
            }
            sc.close();
        }catch(FileNotFoundException e){
        }
        return productList;
    }
}


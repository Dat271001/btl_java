//
//package view;
//
//import controller.UserManager;
//import model.User;
//import java.io.*;
//import javax.swing.*;
//import java.awt.*;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//
//public class QRCodeScreen extends JFrame {
//    private User user;
//
//    public QRCodeScreen(User user){
//        this.user = user;
//
//        setTitle("Deposit Money");
//        setSize(300, 300);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        // Giao diện chính
//        JPanel mainPanel = new JPanel(new BorderLayout());
//
//        // Thêm tiêu đề
//        JLabel titleLabel = new JLabel("Deposit Money", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
//        mainPanel.add(titleLabel, BorderLayout.NORTH);
//
//        // Tạo panel cho các trường nhập liệu
//        JPanel inputPanel = new JPanel();
//        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
//
//        // Thêm combo box chọn ngân hàng
//        JLabel bankLabel = new JLabel("Choose Bank:");
//        JComboBox<String> bankComboBox = new JComboBox<>(new String[]{"Bank A", "Bank B", "Bank C"});
//        inputPanel.add(bankLabel);
//        inputPanel.add(bankComboBox);
//
//        // Thêm trường nhập số tài khoản
//        JLabel accountLabel = new JLabel("Account Number:");
//        JTextField accountField = new JTextField();
//        inputPanel.add(accountLabel);
//        inputPanel.add(accountField);
//
//        // Thêm combo box chọn số tiền
//        JLabel amountLabel = new JLabel("Deposit Amount:");
//        JComboBox<String> amountComboBox = new JComboBox<>(new String[]{"100000", "200000", "500000", "1000000"});
//        inputPanel.add(amountLabel);
//        inputPanel.add(amountComboBox);
//
//        // Thêm nút xác nhận nạp
//        JButton confirmButton = new JButton("Confirm Deposit");
//        confirmButton.addActionListener(e -> {
//        String selectedBank = (String) bankComboBox.getSelectedItem();
//        String accountNumber = accountField.getText();
//        String depositAmount = (String) amountComboBox.getSelectedItem();
//        
//        
//        if (accountNumber.isEmpty()) {
//        JOptionPane.showMessageDialog(this, "Please enter your account number.");
//        } 
//        else{
////            try {     
////                UserManager x = new UserManager();
////                
////                HashMap<String, User> map = x.getUsers();
////                User y = map.get("1");
////                y.addBalance(Double.parseDouble(depositAmount));
////                
////                
////               
////                
////            } catch (FileNotFoundException ex) {
////                System.out.println("Co loi xay ra!");
////            }
//              JOptionPane.showMessageDialog(this, "Deposit Successful:\n"
//                + "Bank: " + selectedBank + "\n"
//                + "Account: " + accountNumber + "\n"
//                + "Amount: " + depositAmount);
//        }
//        });
//        inputPanel.add(new JLabel()); // Thêm một ô trống để căn nút vào giữa
//        inputPanel.add(confirmButton);
//
//        mainPanel.add(inputPanel, BorderLayout.CENTER);
//        add(mainPanel);
//        setVisible(true);
//
//
//        // Thêm mã QR
//        JLabel qrLabel = new JLabel(new ImageIcon("src\\main\\java\\img\\QR code.jpg")); // Đường dẫn đến ảnh mã QR của bạn
//        mainPanel.add(qrLabel, BorderLayout.CENTER);
//
//        add(mainPanel);
//        setVisible(true);
//    }
//}
//
//

package view;

import controller.UserManager;
import model.User;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class QRCodeScreen extends JFrame {
    private User user;
    private JComboBox<String> amountComboBox = new JComboBox<>(new String[]{"100000", "200000", "500000", "1000000"});
    private JComboBox<String> bankComboBox = new JComboBox<>(new String[]{"Bank A", "Bank B", "Bank C"});
    private JButton confirmButton = new JButton("Confirm Deposit");

    public User getUser() {
        return user;
    }

    
    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JComboBox<String> getAmountComboBox() {
        return amountComboBox;
    }
    
    
    public QRCodeScreen(User user){
        this.user = user;
        setTitle("Deposit Money");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Giao diện chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Thêm tiêu đề
        JLabel titleLabel = new JLabel("Deposit Money", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Tạo panel cho các trường nhập liệu
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Thêm combo box chọn ngân hàng
        JLabel bankLabel = new JLabel("Choose Bank:");
        JComboBox<String> bankComboBox = new JComboBox<>(new String[]{"Bank A", "Bank B", "Bank C"});
        inputPanel.add(bankLabel);
        inputPanel.add(bankComboBox);

        // Thêm trường nhập số tài khoản
        JLabel accountLabel = new JLabel("Account Number:");
        JTextField accountField = new JTextField();
        inputPanel.add(accountLabel);
        inputPanel.add(accountField);

        // Thêm combo box chọn số tiền
        JLabel amountLabel = new JLabel("Deposit Amount:");
        JComboBox<String> amountComboBox = new JComboBox<>(new String[]{"100000", "200000", "500000", "1000000"});
        inputPanel.add(amountLabel);
        inputPanel.add(amountComboBox);

        // Thêm nút xác nhận nạp
        JButton confirmButton = new JButton("Confirm Deposit");
        confirmButton.addActionListener(e -> {
        String selectedBank = (String) bankComboBox.getSelectedItem();
        String accountNumber = accountField.getText();
        String depositAmount = (String) amountComboBox.getSelectedItem();
        
        if (accountNumber.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter your account number.");
        } 
        else{
              user.addBalance(Double.parseDouble(depositAmount));
              JOptionPane.showMessageDialog(this, "Deposit Successful:\n"
                + "Bank: " + selectedBank + "\n"
                + "Acount: " + accountNumber + "\n"
                + "Amount: " + String.format("%.2f", user.getBalance()));
              
        }
        
        });
        inputPanel.add(new JLabel()); // Thêm một ô trống để căn nút vào giữa
        inputPanel.add(confirmButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);


        // Thêm mã QR
        JLabel qrLabel = new JLabel(new ImageIcon("src\\main\\java\\img\\QR code.jpg")); // Đường dẫn đến ảnh mã QR của bạn
        mainPanel.add(qrLabel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }
}


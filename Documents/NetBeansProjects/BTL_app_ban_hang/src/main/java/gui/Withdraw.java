


package gui;

import controller.UserManager;
import model.User;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import gui.MainScreenAdmin.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Withdraw extends JFrame {
    private User user;

    public Withdraw(User user){
        this.user = user;

        setTitle("Withdraw Money");
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
        JLabel amountTitle = new JLabel("Account Number:");
        JTextField amountField = new JTextField();
        inputPanel.add(amountTitle);
        inputPanel.add(amountField);

        // Thêm nút xác nhận nạp
        
        
        JButton confirmButton = new JButton("Confirm Deposit");
        
        confirmButton.addActionListener(e -> {
        
            double depositAmount = (double) Double.parseDouble(amountField.getText());
        String selectedBank = (String) bankComboBox.getSelectedItem();
        String accountNumber = accountField.getText();
        
        if (accountNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your account number.");
        }else{

              
              
              if(user.deductBalance(depositAmount)){
                  JOptionPane.showMessageDialog(this, "Deposit Successful:\n"
                + "Bank: " + selectedBank + "\n"
                + "Account: " + accountNumber + "\n"
                + "Amount: " + depositAmount);
                  System.out.println(depositAmount);
//                  user.deductBalance(depositAmount);
                  MainScreenAdmin.depositMenu.setText("$: " + user.getBalance());
                  
                  ArrayList<String> lines = new ArrayList<>();
        File accountFile = new File(new File("src\\main\\java\\controller\\Accs.txt").getAbsolutePath());
        try{
        Scanner sn = new Scanner(accountFile);
        while(sn.hasNextLine()){
            String tmp = sn.nextLine();
            if(tmp.startsWith(user.getUsername()+" "+user.getPassword())){
                tmp = user.getUsername()+" "+user.getPassword()+" "+Double.toString(user.getBalance());
            }
            lines.add(tmp);
        }
        sn.close();
        }catch(FileNotFoundException ex){
            
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\java\\controller\\Accs.txt"))) {
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        } catch (IOException ex) {
        }
              } else{
                  JOptionPane.showMessageDialog(this, "Not enough money !");
              };
              
        }
        });
        inputPanel.add(new JLabel()); // Thêm một ô trống để căn nút vào giữa
        inputPanel.add(confirmButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
        // Cập nhật lại số tiền sau khi nạp
        
        
        
        // Thêm mã QR
        JLabel qrLabel = new JLabel(new ImageIcon("src\\main\\java\\img\\QR code.jpg")); // Đường dẫn đến ảnh mã QR của bạn
        mainPanel.add(qrLabel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }
}
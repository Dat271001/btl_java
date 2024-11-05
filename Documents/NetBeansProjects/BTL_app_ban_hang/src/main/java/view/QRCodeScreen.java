
package view;

import controller.UserManager;
import model.User;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import view.MainScreen.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class QRCodeScreen extends JFrame {
    private User user;
    private String tm="0";
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
        JComboBox<String> amountComboBox = new JComboBox<>(new String[]{"100", "200", "500", "1000"});
        inputPanel.add(amountLabel);
        inputPanel.add(amountComboBox);
       

        // Thêm nút xác nhận nạp
        
        
        JButton confirmButton = new JButton("Confirm Deposit");
        
        confirmButton.addActionListener(e -> {
        
        String depositAmount = (String) amountComboBox.getSelectedItem();
        String selectedBank = (String) bankComboBox.getSelectedItem();
        String accountNumber = accountField.getText();
        
        if (accountNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your account number.");
        }else{

              JOptionPane.showMessageDialog(this, "Deposit Successful:\n"
                + "Bank: " + selectedBank + "\n"
                + "Account: " + accountNumber + "\n"
                + "Amount: " + depositAmount);

                user.addBalance(Double.parseDouble(depositAmount));
                ArrayList<String> lines = new ArrayList<>();
        File accountFile = new File(new File("src\\main\\java\\files\\Accs.txt").getAbsolutePath());
        try{
        Scanner sn = new Scanner(accountFile);
        while(sn.hasNextLine()){
            String tmp = sn.nextLine();
            System.out.println(tmp+" "+user.getUsername()+" "+user.getPassword());
            if(tmp.startsWith(user.getUsername()+" "+user.getPassword())){
                tmp = user.getUsername()+" "+user.getPassword()+" "+Double.toString(user.getBalance());
            }
            lines.add(tmp);
        }
        sn.close();
        }catch(FileNotFoundException ex){
            
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\java\\files\\Accs.txt"))) {
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        } catch (IOException ex) {
        }
                MainScreen.depositMenu.setText("$: " + user.getBalance());
        }
        });
        inputPanel.add(new JLabel()); // Thêm một ô trống để căn nút vào giữa
        inputPanel.add(confirmButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
        // Cập nhật lại số tiền sau khi nạp
        
        JLabel qrLabel = new JLabel(new ImageIcon("src\\main\\java\\img\\QR code.jpg")); 
        mainPanel.add(qrLabel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }
}
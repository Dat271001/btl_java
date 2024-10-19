package gui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class QRCodeScreen extends JFrame {
    private User user;

    public QRCodeScreen(User user) {
        this.user = user;

        setTitle("Deposit Money");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Giao diện chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Thêm tiêu đề
        JLabel titleLabel = new JLabel("Deposit Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Thêm mã QR
        JLabel qrLabel = new JLabel(new ImageIcon("path/to/your/qrcode.png")); // Đường dẫn đến ảnh mã QR của bạn
        mainPanel.add(qrLabel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }
}


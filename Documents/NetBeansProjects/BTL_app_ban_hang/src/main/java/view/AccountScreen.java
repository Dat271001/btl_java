package view;

import model.User;
import controller.Cart;
import model.Product;
import controller.UserManager; // Thêm import cho UserManager

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountScreen extends JFrame {
    private User user;
    private Cart cart; // Khai báo biến cart
    private UserManager userManager; // Khai báo biến UserManager
    private JTextField addressField, phoneField;

    // Thay đổi hàm khởi tạo để nhận thêm tham số Cart và UserManager
    public AccountScreen(User user, Cart cart, UserManager userManager) {
        this.user = user;
        this.cart = cart; // Gán giá trị cho biến cart
        this.userManager = userManager; // Gán giá trị cho biến UserManager

        setTitle("Account Information");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Giao diện chính
        JPanel mainPanel = new JPanel(new GridLayout(5, 2)); // Thay đổi số hàng ở đây

        // Tên tài khoản
        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(new JLabel(user.getUsername()));

        // Địa chỉ
        mainPanel.add(new JLabel("Address:"));
        addressField = new JTextField(user.getAddress() == null ? "" : user.getAddress());
        mainPanel.add(addressField);

        // Số điện thoại
        mainPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField(user.getPhone() == null ? "" : user.getPhone()); // Hiển thị số điện thoại nếu có
        mainPanel.add(phoneField);

        // Nút Cập nhật
        JButton updateButton = new JButton("Update Information");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cập nhật thông tin người dùng
                String address = addressField.getText();
                String phone = phoneField.getText();
                user.setAddress(address);
                user.setPhone(phone); // Lưu số điện thoại vào User

                JOptionPane.showMessageDialog(null, "Information updated successfully!");
            }
        });
        mainPanel.add(updateButton);

        // Nút Lịch sử mua hàng
        JButton purchaseHistoryButton = new JButton("Purchase History");
        purchaseHistoryButton.addActionListener(e -> {
            StringBuilder purchaseHistory = new StringBuilder();
            for (Product product : cart.getPurchasedProducts()) {
                purchaseHistory.append(product.getName())
                               .append(" - Quantity: ")
                               .append(product.getQuantity())
                               .append(" - Price: $")
                               .append(product.getPrice() * product.getQuantity())
                               .append("\n");
            }
            if (cart.getPurchasedProducts().isEmpty()) {
                purchaseHistory.append("No purchase history available.");
            }
            // Hiển thị danh sách sản phẩm đã thanh toán
            JOptionPane.showMessageDialog(null, purchaseHistory.toString(), "Purchase History", JOptionPane.INFORMATION_MESSAGE);
        });
        mainPanel.add(purchaseHistoryButton);

        add(mainPanel);
        setVisible(true);
    }
}

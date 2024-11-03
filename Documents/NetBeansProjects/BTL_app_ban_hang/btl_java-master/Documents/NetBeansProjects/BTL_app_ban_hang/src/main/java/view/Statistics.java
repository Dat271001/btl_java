package view;

import controller.Cart;
import model.PurchaseHistory;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Statistics extends JFrame {
    private Cart cart;

    public Statistics(Cart cart) {
        this.cart = cart;

        setTitle("Purchase History");
        setSize(800, 600); // Tăng kích thước để phù hợp với hình ảnh
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<PurchaseHistory> purchaseHistories = cart.getPurchaseHistories();
        for (PurchaseHistory history : purchaseHistories) {
            StringBuilder details = new StringBuilder();
            details.append("Date: ").append(history.getPurchaseDate()).append("\n");
            details.append("Total Amount: $").append(history.getTotalAmount()).append("\n");
            details.append("Purchased Products:\n");

            // Tạo panel cho từng lịch sử mua hàng
            JPanel historyPanel = new JPanel();
            historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));

            for (Product product : history.getPurchasedProducts()) {
                // Tạo panel cho mỗi sản phẩm
                JPanel productPanel = new JPanel();
                productPanel.setLayout(new BorderLayout());
                
                // Hiển thị hình ảnh sản phẩm
                JLabel imageLabel = new JLabel();
                ImageIcon productImage = new ImageIcon(product.getImagePath()); // Đường dẫn tới hình ảnh
                Image scaledImage = productImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                
                // Hiển thị tên và giá sản phẩm
                JLabel productDetailsLabel = new JLabel(product.getName() + " - $" + product.getPrice());
                
                // Thêm các thành phần vào productPanel
                productPanel.add(imageLabel, BorderLayout.WEST);
                productPanel.add(productDetailsLabel, BorderLayout.CENTER);

                // Thêm productPanel vào historyPanel
                historyPanel.add(productPanel);
            }

            // Thêm chi tiết lịch sử mua hàng vào panel chính
            JTextArea textArea = new JTextArea(details.toString());
            textArea.setEditable(false);
            historyPanel.add(textArea);

            panel.add(historyPanel);
        }

        add(new JScrollPane(panel));
        setVisible(true);
    }
}

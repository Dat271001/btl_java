package view;

import controller.Cart;
import model.PurchaseHistory;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PurchaseHistoryScreen extends JFrame {
    private Cart cart;

    public PurchaseHistoryScreen(Cart cart) {
        this.cart = cart;

        setTitle("Purchase History");
        setSize(800, 600); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<PurchaseHistory> purchaseHistories = cart.getPurchaseHistories();
        for (PurchaseHistory history : purchaseHistories) {
            // Tạo panel cho từng lịch sử mua hàng
            JPanel historyPanel = new JPanel();
            historyPanel.setLayout(new BorderLayout());
            historyPanel.setBorder(BorderFactory.createTitledBorder("Date: " + history.getPurchaseDate()));

            // Thông tin tổng số tiền
            JLabel totalAmountLabel = new JLabel("Total Amount: $" + history.getTotalAmount());
            totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
            historyPanel.add(totalAmountLabel, BorderLayout.NORTH);

            // Tạo bảng cho các sản phẩm đã mua
            String[] columnNames = {"Product Name", "Quantity", "Price", "Total Price"};
            Object[][] data = new Object[history.getPurchasedProducts().size()][4];

            int index = 0;
            for (Product product : history.getPurchasedProducts()) {
                int quantity = product.getQuantity(); // Giả sử bạn có phương thức getQuantity trong Product
                double totalPrice = quantity * product.getPrice();

                data[index][0] = product.getName();
                data[index][1] = quantity;
                data[index][2] = "$" + product.getPrice();
                data[index][3] = "$" + totalPrice;
                index++;
            }

            JTable productTable = new JTable(data, columnNames);
            productTable.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(productTable);
            historyPanel.add(scrollPane, BorderLayout.CENTER);

            // Thêm historyPanel vào panel chính
            panel.add(historyPanel);
        }

        add(new JScrollPane(panel));
        setVisible(true);
    }
}
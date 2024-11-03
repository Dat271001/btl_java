package view;

import controller.Cart;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CartScreen extends JFrame {
    private Cart cart;

    public CartScreen(Cart cart) {
        this.cart = cart;

        setTitle("Cart");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Giao diện chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Product Name", "Price", "Size", "Quantity"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){
        @Override
          public boolean isCellEditable(int row, int column) {
        // Trả về false để không cho phép sửa ô nào trong bảng
        return false;
        }
        };
        JTable cartTable = new JTable(tableModel);
        
        // Thêm sản phẩm vào bảng giỏ hàng
        for (Product product : cart.getProducts()) {
            Object[] row = {product.getName(), product.getPrice(), product.getSize(), product.getQuantity()};
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(cartTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Nút xóa giỏ hàng
        JButton clearCartButton = new JButton("Clear Cart");
        clearCartButton.addActionListener(e -> {
            cart.clear();  // Gọi phương thức clear trong Cart
            tableModel.setRowCount(0);  // Xóa tất cả sản phẩm trong bảng
            JOptionPane.showMessageDialog(this, "Cart cleared!");
        });
        
        mainPanel.add(clearCartButton, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }
}

package view;

import controller.Cart;
import model.PurchaseHistory;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class StatisticsScreen extends JFrame {
    private Cart cart;

    public StatisticsScreen(Cart cart) {
        this.cart = cart;

        setTitle("Thống kê số sản phẩm đã bán");
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
        
        for (Product product : cart.getProducts()) {
            Object[] row = {product.getName(), product.getPrice(), product.getSize(), product.getQuantity()};
            tableModel.addRow(row);
        }
        
        JScrollPane scrollPane = new JScrollPane(cartTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(mainPanel);
        setVisible(true);
        
    }
}
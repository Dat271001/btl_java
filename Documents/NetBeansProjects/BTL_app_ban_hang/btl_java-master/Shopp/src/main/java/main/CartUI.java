package main;

import cart.Cart;
import cart.CartItem;
import payment.Payment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartUI extends JFrame {
    private Cart cart;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField, quantityField, priceField, sizeField; // Thêm sizeField
    private JLabel totalCostLabel;

    public CartUI() {
        cart = new Cart();
        setTitle("Shopping Cart");
        setSize(700, 400); // Điều chỉnh kích thước cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Bảng hiển thị sản phẩm trong giỏ hàng
        tableModel = new DefaultTableModel(new String[]{"Product Name", "Quantity", "Price", "Size", "Total"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bảng nhập liệu cho sản phẩm mới
        JPanel inputPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        inputPanel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Size:")); // Nhãn cho size
        sizeField = new JTextField();
        inputPanel.add(sizeField);

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new AddItemListener());
        inputPanel.add(addButton);

        totalCostLabel = new JLabel("Total Cost: $0.00");
        inputPanel.add(totalCostLabel);

        add(inputPanel, BorderLayout.NORTH);

        // Các nút thao tác
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton removeButton = new JButton("Remove Item");
        removeButton.addActionListener(new RemoveItemListener());
        buttonPanel.add(removeButton);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new CheckoutListener());
        buttonPanel.add(checkoutButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    CartUI(Cart cart) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Cập nhật tổng chi phí trong giao diện
    private void updateTotalCost() {
        totalCostLabel.setText("Total Cost: $" + String.format("%.2f", cart.getTotalCost()));
    }

    // Thêm một sản phẩm mới vào giỏ hàng và cập nhật giao diện
    private class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            String size = sizeField.getText(); // Lấy giá trị kích thước

            CartItem item = new CartItem(name, quantity, price, size);
            cart.addItem(item);

            tableModel.addRow(new Object[]{name, quantity, price, size, item.getTotalPrice()});
            updateTotalCost();

            // Reset input fields
            nameField.setText("");
            quantityField.setText("");
            priceField.setText("");
            sizeField.setText("");
        }
    }

    // Xóa sản phẩm đã chọn khỏi giỏ hàng và cập nhật giao diện
    private class RemoveItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String productName = (String) tableModel.getValueAt(selectedRow, 0);
                cart.removeItem(productName);
                tableModel.removeRow(selectedRow);
                updateTotalCost();
            }
        }
    }

    // Xử lý thanh toán
    // Xử lý thanh toán
private class CheckoutListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (cart.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(CartUI.this, "Cart is empty. Please add items to proceed.");
        } else {
            PaymentUI paymentUI = new PaymentUI(cart);
            paymentUI.setVisible(true);
        }
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CartUI cartUI = new CartUI();
            cartUI.setVisible(true);
        });
    }
}

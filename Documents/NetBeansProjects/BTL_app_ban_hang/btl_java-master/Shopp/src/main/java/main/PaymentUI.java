package main;

import cart.Cart;
import cart.CartItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PaymentUI extends JFrame {
    private Cart cart;
    private JTextField addressField, discountCodeField;
    private JComboBox<String> paymentMethodCombo;
    private JLabel totalCostLabel, shippingFeeLabel, finalTotalLabel;

    public PaymentUI(Cart cart) {
        this.cart = cart;
        setTitle("Payment");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Hiển thị thông tin sản phẩm
        JPanel itemPanel = new JPanel(new GridLayout(0, 1));
        itemPanel.setBorder(BorderFactory.createTitledBorder("Items"));
        List<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            itemPanel.add(new JLabel(item.toString()));
        }
        add(itemPanel, BorderLayout.NORTH);

        // Bảng thông tin thanh toán
        JPanel paymentPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Payment Details"));

        paymentPanel.add(new JLabel("Total Amount:"));
        totalCostLabel = new JLabel(String.format("$%.2f", cart.getTotalCost()));
        paymentPanel.add(totalCostLabel);

        paymentPanel.add(new JLabel("Shipping Fee:"));
        shippingFeeLabel = new JLabel("$5.00"); // Giả sử phí vận chuyển cố định là 5$
        paymentPanel.add(shippingFeeLabel);

        paymentPanel.add(new JLabel("Discount Code:"));
        discountCodeField = new JTextField();
        paymentPanel.add(discountCodeField);

        paymentPanel.add(new JLabel("Delivery Address:"));
        addressField = new JTextField();
        paymentPanel.add(addressField);

        paymentPanel.add(new JLabel("Payment Method:"));
        paymentMethodCombo = new JComboBox<>(new String[]{"Credit Card", "Debit Card", "Cash on Delivery", "E-wallet"});
        paymentPanel.add(paymentMethodCombo);

        add(paymentPanel, BorderLayout.CENTER);

        // Nút xác nhận thanh toán
        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("Confirm Payment");
        confirmButton.addActionListener(new ConfirmPaymentListener());
        buttonPanel.add(confirmButton);

        // Tổng số tiền thanh toán cuối cùng
        finalTotalLabel = new JLabel("Final Total: $" + calculateFinalTotal());
        buttonPanel.add(finalTotalLabel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Tính toán tổng số tiền phải thanh toán
    private double calculateFinalTotal() {
        double total = cart.getTotalCost() + 5.00; // Thêm phí vận chuyển
        // Kiểm tra mã giảm giá (giả sử giảm giá 10% nếu có mã)
        String discountCode = discountCodeField.getText();
        if (discountCode.equalsIgnoreCase("DISCOUNT10")) {
            total *= 0.9; // Giảm 10%
        }
        return total;
    }

    // Xử lý khi người dùng nhấn "Confirm Payment"
    private class ConfirmPaymentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double finalTotal = calculateFinalTotal();
            String address = addressField.getText();
            String paymentMethod = (String) paymentMethodCombo.getSelectedItem();

            // Kiểm tra thông tin nhập vào
            if (address.isEmpty()) {
                JOptionPane.showMessageDialog(PaymentUI.this, "Please enter the delivery address.");
                return;
            }

            // Thực hiện thanh toán (giả lập)
            JOptionPane.showMessageDialog(PaymentUI.this,
                    "Payment successful!\nTotal: $" + String.format("%.2f", finalTotal) +
                            "\nPayment Method: " + paymentMethod +
                            "\nShipping to: " + address);

            // Đóng cửa sổ thanh toán
            dispose();
        }
    }
}

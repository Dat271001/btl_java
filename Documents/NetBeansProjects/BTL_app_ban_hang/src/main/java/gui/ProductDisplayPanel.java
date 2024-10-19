package gui;

import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductDisplayPanel extends JPanel {
    private List<Product> products;
    private int currentPage = 0;
    private final int itemsPerPage = 5;

    public ProductDisplayPanel(List<Product> products) {
        this.products = products;
        setLayout(new GridLayout(0, 1));
        updateDisplay();
    }

    private void updateDisplay() {
        removeAll();
        int start = currentPage * itemsPerPage;
        int end = Math.min(start + itemsPerPage, products.size());

        for (int i = start; i < end; i++) {
            Product product = products.get(i);
            JLabel nameLabel = new JLabel(product.getName() + " - $" + product.getPrice());

            // Hiển thị hình ảnh
            ImageIcon icon = new ImageIcon(product.getImagePath());
            JLabel imageLabel = new JLabel(icon);

            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BorderLayout());
            productPanel.add(nameLabel, BorderLayout.NORTH);
            productPanel.add(imageLabel, BorderLayout.CENTER);

            add(productPanel);
        }

        revalidate();
        repaint();
    }

    public void nextPage() {
        if (currentPage < products.size() / itemsPerPage) {
            currentPage++;
            updateDisplay();
        }
    }

    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            updateDisplay();
        }
    }
}

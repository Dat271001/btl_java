package view;

import controller.Cart;
import model.Product;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsScreen extends JFrame {
    private Cart cart;
    private ChartPanel chartPanel; // Declare chartPanel to update it later
    private boolean isPieChart = true; // Track the current chart type

    public StatisticsScreen(Cart cart) {
        this.cart = cart;

        setTitle("Thống kê số sản phẩm đã bán");
        setSize(800, 700);  // Increase window size for more chart space
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create statistics table
        String[] columnNames = {"Product Name", "Price", "Size", "Quantity"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable cartTable = new JTable(tableModel);
        for (Product product : cart.getProducts()) {
            Object[] row = {product.getName(), product.getPrice(), product.getSize(), product.getQuantity()};
            tableModel.addRow(row);
        }
        
        JScrollPane scrollPane = new JScrollPane(cartTable);
        mainPanel.add(scrollPane, BorderLayout.NORTH);

        // Create initial chart (pie chart)
        JFreeChart pieChart = createPieChart();
        chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(750, 400));  // Increase chart size
        
        mainPanel.add(chartPanel, BorderLayout.CENTER);  // Add chart below the table

        // Create toggle button
        JButton toggleButton = new JButton("Switch to Bar Chart");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPieChart) {
                    // Switch to bar chart
                    JFreeChart barChart = createBarChart();
                    chartPanel.setChart(barChart);
                    toggleButton.setText("Switch to Pie Chart");
                } else {
                    // Switch to pie chart
                    JFreeChart pieChart = createPieChart();
                    chartPanel.setChart(pieChart);
                    toggleButton.setText("Switch to Bar Chart");
                }
                isPieChart = !isPieChart; // Toggle the chart type
            }
        });

        mainPanel.add(toggleButton, BorderLayout.SOUTH); // Add the button at the bottom
        add(mainPanel);
        setVisible(true);
    }

    // Function to create dataset for pie chart
    private JFreeChart createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        for (Product product : cart.getProducts()) {
            dataset.setValue(product.getName(), product.getQuantity());
        }
        
        return ChartFactory.createPieChart(
                "Chart of the number of products sold",  // Chart title
                dataset,  // Data
                true,  // Show legend
                true,
                false);
    }

    // Function to create dataset for bar chart
    private JFreeChart createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (Product product : cart.getProducts()) {
            dataset.addValue(product.getQuantity(), "Quantity", product.getName());
        }
        
        return ChartFactory.createBarChart(
                "Chart of the number of products sold", // Chart title
                "Sản phẩm", // X-axis label
                "Số lượng", // Y-axis label
                dataset);
    }
}

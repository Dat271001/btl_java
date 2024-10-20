/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainUI;

import java.awt.*;
import javax.swing.*;
//import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class MainScreen extends JFrame{
    private String info;

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
     
    public MainScreen(String info) throws HeadlessException {
        this.info = info;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 600);
        this.setTitle("BTL_Java");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setVisible(true);
        searchPanel.setSize(1200,100);
        searchPanel.setLocation(0, 0);
        searchPanel.setBackground(Color.red);
        
        JButton homePage = new JButton();
        homePage.setVisible(true);
        homePage.setSize(100, 70);
        homePage.setLocation(50, 15);
        homePage.setText("Home");
        homePage.setFont(new Font("Segoe UI", Font.BOLD, 12));
        homePage.setBackground(Color.red);
        searchPanel.add(homePage);
        
        JTextField searchBar = new JTextField();
        searchBar.setVisible(true);
        searchBar.setSize(700, 40);
        searchBar.setLocation(200, 30);
        searchPanel.add(searchBar);
        
        JButton searchButton = new JButton();
        searchButton.setVisible(true);
        searchButton.setSize(70, 40);
        searchButton.setLocation(920, 30);
        searchButton.setText("Q");
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchButton.setBackground(Color.white);
        searchPanel.add(searchButton);
        
        JButton profileButton = new JButton();
        profileButton.setVisible(true);
        profileButton.setSize(70, 40);
        profileButton.setLocation(1040, 30);
        profileButton.setText("Profile");
        profileButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchPanel.add(profileButton);
        
        JButton cartButton = new JButton();
        cartButton.setVisible(true);
        cartButton.setSize(70, 40);
        cartButton.setLocation(1110, 30);
        cartButton.setText("Cart");
        cartButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchPanel.add(cartButton);
        
        JLabel infoField = new JLabel();
        infoField.setVisible(true);
        infoField.setSize(300, 50);
        infoField.setLocation(500, 250);
        infoField.setText(this.info);
        infoField.setFont(new Font("Segoe UI", Font.BOLD, 28));
        this.add(infoField);
        
        this.add(searchPanel);
        this.setVisible(true);
    }
    
}

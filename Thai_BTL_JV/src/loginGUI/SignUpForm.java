/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginGUI;

import btl_java.BTL_Java;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 *
 * @author PC
 */
public class SignUpForm extends JFrame{
    JLabel image;
    JPanel signUpForm;
    ImageIcon logo = new ImageIcon("D:\\New Folder\\BTL_Java\\src\\img\\Logo.jpg");
    
    public SignUpForm() throws HeadlessException {
//        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 450);
        this.setTitle("BTL_Java");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
//        this.getContentPane().setBackground(Color.white);
        
        //image
        image = new JLabel();
        image.setVisible(true);
//        image.setText("JLabel");
//        image.setBackground(Color.red);
//        image.setOpaque(true);
        image.setSize(400, 450);
        image.setLocation(0, 0);
        image.setIcon(logo);
        
        //LoginFormPanel
        signUpForm = new JPanel();
        signUpForm.setLayout(null);
        signUpForm.setSize(400,450);
        signUpForm.setLocation(400, 0);
        
        //LoginTitle
        JLabel signUpTitle = new JLabel();
        signUpTitle.setVisible(true);
        signUpTitle.setSize(400, 40);
        signUpTitle.setLocation(0, 55);
        signUpTitle.setText("Sign Up");
        signUpTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        //Username
        signUpTitle.setHorizontalAlignment(JLabel.CENTER);
        signUpTitle.setHorizontalTextPosition(JLabel.CENTER);
        signUpTitle.setVerticalAlignment(JLabel.TOP);
        signUpForm.add(signUpTitle);
        
        //Username
        JLabel userNameTitle = new JLabel();
        userNameTitle.setVisible(true);
        userNameTitle.setSize(300, 20);
        userNameTitle.setLocation(50, 110);
        userNameTitle.setText("Username:");
        userNameTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        signUpForm.add(userNameTitle);
        
        JTextField userNameField = new JTextField();
        userNameField.setVisible(true);
        userNameField.setSize(300, 35);
        userNameField.setLocation(50, 130);
        signUpForm.add(userNameField);
        
        //Password
        JLabel passwordTitle = new JLabel();
        passwordTitle.setVisible(true);
        passwordTitle.setSize(300, 20);
        passwordTitle.setLocation(50, 170);
        passwordTitle.setText("Password:");
        passwordTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        signUpForm.add(passwordTitle);
        
        JTextField passwordField = new JTextField();
        passwordField.setVisible(true);
        passwordField.setSize(300, 35);
        passwordField.setLocation(50, 190);
        signUpForm.add(passwordField);
        
        //SignUp
        JLabel signUpAsField = new JLabel();
        signUpAsField.setVisible(true);
        signUpAsField.setSize(300, 20);
        signUpAsField.setLocation(50, 240);
        signUpAsField.setText("----------Sign Up as ?----------");
        signUpAsField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        signUpAsField.setHorizontalAlignment(JLabel.CENTER);
        signUpForm.add(signUpAsField);
        
        JButton buyerSignUpButton = new JButton();
        buyerSignUpButton.setVisible(true);
        buyerSignUpButton.setSize(130, 30);
        buyerSignUpButton.setLocation(50, 270);
        buyerSignUpButton.setText("Buyer");
        buyerSignUpButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        signUpForm.add(buyerSignUpButton);
        
        JButton sellerSignUpButton = new JButton();
        sellerSignUpButton.setVisible(true);
        sellerSignUpButton.setSize(130, 30);
        sellerSignUpButton.setLocation(220, 270);
        sellerSignUpButton.setText("Seller");
        sellerSignUpButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        signUpForm.add(sellerSignUpButton);
        
        //Log In Change
        JLabel loginChangeTitle = new JLabel();
        loginChangeTitle.setVisible(true);
        loginChangeTitle.setSize(150, 20);
        loginChangeTitle.setLocation(50, 320);
        loginChangeTitle.setText("Already have account?");
        loginChangeTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        loginChangeTitle.setHorizontalAlignment(JLabel.CENTER);
        signUpForm.add(loginChangeTitle);
        
        JButton loginChangeButton = new JButton();
        loginChangeButton.setVisible(true);
        loginChangeButton.setSize(130, 30);
        loginChangeButton.setLocation(220, 320);
        loginChangeButton.setText("Log In");
        loginChangeButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        signUpForm.add(loginChangeButton);
        
        //add components
        this.add(image);
        this.add(signUpForm);
//        this.pack();
        this.setVisible(true);
        
        loginChangeButton.addActionListener(e ->{
            LoginForm tmp = new LoginForm();
            this.dispose();
        });
        
        buyerSignUpButton.addActionListener(e ->{
            String username = userNameField.getText();
            String pass = passwordField.getText();
//            System.out.println(username + " -- " + pass);
            
            boolean checkFlag = BTL_Java.accountProcess.PasswordCheck(username, pass);
            if(checkFlag) JOptionPane.showMessageDialog(null, "Account existed!");
            else{
                try {
                    BTL_Java.accountProcess.AddAccount(username, pass, "B");
                } catch (IOException ex) {
//                    Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Sign Up successful!");
            }
        });
        
        sellerSignUpButton.addActionListener(e ->{
            String username = userNameField.getText();
            String pass = passwordField.getText();
//            System.out.println(username + " -- " + pass);
            
            boolean checkFlag = BTL_Java.accountProcess.PasswordCheck(username, pass);
            if(checkFlag) JOptionPane.showMessageDialog(null, "Account existed!");
            else{
                try {
                    BTL_Java.accountProcess.AddAccount(username, pass, "B");
                } catch (IOException ex) {
//                    Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Sign Up successful!");
            }
        });
}
}

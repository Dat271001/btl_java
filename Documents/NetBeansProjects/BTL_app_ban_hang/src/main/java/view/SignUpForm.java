
package view;

import controller.Main;
import java.awt.*;
import java.io.File;
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
    ImageIcon originalIcon = new ImageIcon(new File("src\\main\\java\\img\\MAIN.png").getAbsolutePath());
    Image scaledImage = originalIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
    ImageIcon logo = new ImageIcon(scaledImage);
    
    public SignUpForm() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 450);
        this.setTitle("BTL_Java");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        //image
        image = new JLabel();
        image.setVisible(true);
        image.setBackground(new Color(39, 35, 67));
        image.setOpaque(true);
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
        //Password Confirm
        JLabel confirmTitle = new JLabel();
        confirmTitle.setVisible(true);
        confirmTitle.setSize(300, 20);
        confirmTitle.setLocation(50, 230);
        confirmTitle.setText("Confirm Password:");
        confirmTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        signUpForm.add(confirmTitle);
        
        JTextField confirmField = new JTextField();
        confirmField.setVisible(true);
        confirmField.setSize(300, 35);
        confirmField.setLocation(50, 250);
        signUpForm.add(confirmField);
        
        //SignUp
        JButton signUpButton = new JButton();
        signUpButton.setVisible(true);
        signUpButton.setSize(300, 30);
        signUpButton.setLocation(50, 300);
        signUpButton.setText("Sign Up");
        signUpButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        signUpForm.add(signUpButton);
        
        
        //Log In Change
        JLabel loginChangeTitle = new JLabel();
        loginChangeTitle.setVisible(true);
        loginChangeTitle.setSize(300, 20);
        loginChangeTitle.setLocation(50, 330);
        loginChangeTitle.setText("Already have account?");
        loginChangeTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        loginChangeTitle.setHorizontalAlignment(JLabel.CENTER);
        signUpForm.add(loginChangeTitle);
        
        JButton loginChangeButton = new JButton();
        loginChangeButton.setVisible(true);
        loginChangeButton.setSize(100, 30);
        loginChangeButton.setLocation(150, 360);
        loginChangeButton.setText("Log In");
        loginChangeButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        signUpForm.add(loginChangeButton);
        
        //add components
        this.add(image);
        this.add(signUpForm);
        this.setVisible(true);
        
        loginChangeButton.addActionListener(e ->{
            LoginForm tmp = new LoginForm();
            this.dispose();
        });
        
        signUpButton.addActionListener(e ->{
            String username = userNameField.getText();
            String pass = passwordField.getText();
            String confirm = confirmField.getText();
            if(!pass.equals(confirm)){
                JOptionPane.showMessageDialog(this, "Password does not match!");
            } else{
                boolean checkFlag = Main.userManager.AccountCheck(username, pass);
                if(pass.length()<6) JOptionPane.showMessageDialog(this, "Password must longer than 6 characters!");
                else{
                    if(checkFlag) JOptionPane.showMessageDialog(this, "Account existed!");
                    else{
                        try {
                            Main.userManager.AddAccount(username, pass);
                        } catch (IOException ex) {
                        }
                        JOptionPane.showMessageDialog(this, "Sign Up successful!");
                    }
                }
                
            }
        });
        
}
}

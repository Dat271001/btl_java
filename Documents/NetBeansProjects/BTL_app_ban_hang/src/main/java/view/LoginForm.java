
package view;
//import java.awt.Color;
import controller.Main;
import controller.*;
import java.awt.*;
import java.awt.HeadlessException;
import javax.swing.*;
import static controller.Main.*;
import java.io.File;
import java.io.IOException;
import model.*;
public class LoginForm extends JFrame{
    JLabel image;
    JPanel loginForm;
    ImageIcon originalIcon = new ImageIcon(new File("src\\main\\java\\img\\MAIN.png").getAbsolutePath());
    Image scaledImage = originalIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
    ImageIcon logo = new ImageIcon(scaledImage);
    
    public LoginForm() throws HeadlessException {
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
        image.setBackground(new Color(39, 35, 67));
        image.setOpaque(true);
        image.setSize(400, 450);
        image.setLocation(0, 0);
        image.setIcon(logo);
        
        //LoginFormPanel
        loginForm = new JPanel();
        loginForm.setLayout(null);
        loginForm.setSize(400,450);
        loginForm.setLocation(400, 0);
        
        //LoginTitle
        JLabel loginTitle = new JLabel();
        loginTitle.setVisible(true);
        loginTitle.setSize(400, 40);
        loginTitle.setLocation(0, 55);
        loginTitle.setText("Log in");
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        //Username
        loginTitle.setHorizontalAlignment(JLabel.CENTER);
        loginTitle.setHorizontalTextPosition(JLabel.CENTER);
        loginTitle.setVerticalAlignment(JLabel.TOP);
        loginForm.add(loginTitle);
        
        //Username
        JLabel userNameTitle = new JLabel();
        userNameTitle.setVisible(true);
        userNameTitle.setSize(300, 20);
        userNameTitle.setLocation(50, 110);
        userNameTitle.setText("Username:");
        userNameTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        loginForm.add(userNameTitle);
        
        JTextField userNameField = new JTextField();
        userNameField.setVisible(true);
        userNameField.setSize(300, 35);
        userNameField.setLocation(50, 130);
        loginForm.add(userNameField);
        
        //Password
        JLabel passwordTitle = new JLabel();
        passwordTitle.setVisible(true);
        passwordTitle.setSize(300, 20);
        passwordTitle.setLocation(50, 170);
        passwordTitle.setText("Password:");
        passwordTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        loginForm.add(passwordTitle);
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setVisible(true);
        passwordField.setSize(300, 35);
        passwordField.setLocation(50, 190);
        loginForm.add(passwordField);
        
        //Login Button
        JButton loginButton = new JButton();
        loginButton.setVisible(true);
        loginButton.setSize(300, 30);
        loginButton.setLocation(50, 240);
        loginButton.setText("Log In");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        loginForm.add(loginButton);
        
        //SignUp Change
        JLabel signUpChangeTitle = new JLabel();
        signUpChangeTitle.setVisible(true);
        signUpChangeTitle.setSize(300, 20);
        signUpChangeTitle.setLocation(50, 300);
        signUpChangeTitle.setText("Don't have an account ?");
        signUpChangeTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        signUpChangeTitle.setHorizontalAlignment(JLabel.CENTER);
        loginForm.add(signUpChangeTitle);
        
        JButton signUpChangeButton = new JButton();
        signUpChangeButton.setVisible(true);
        signUpChangeButton.setSize(100, 30);
        signUpChangeButton.setLocation(150, 330);
        signUpChangeButton.setText("Sign Up");
        signUpChangeButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        loginForm.add(signUpChangeButton);
        
        //add components
        this.add(image);
        this.add(loginForm);
//        this.pack();
        this.setVisible(true);
        
        //Action Listeners
        signUpChangeButton.addActionListener(e ->{
            SignUpForm tmp = new SignUpForm();
            this.dispose();
        });
        
        loginButton.addActionListener(e ->{
            String username = userNameField.getText();
            String pass = new String(passwordField.getPassword());
            boolean checkFlag = Main.userManager.PasswordCheck(username, pass);
            boolean check = Main.userManager.AdminCheck(username, pass);;
            if(checkFlag&&!check) {
                JOptionPane.showMessageDialog(null, "Login user successful!");
//                String info = Main.userManager.GetInfo(username);
//                System.out.println(info);
                User user= userManager.GetUser(username);
                Main.mainScreen = new MainScreen(user, userManager);
                this.dispose();
            }
            else{
            if(check) {
                JOptionPane.showMessageDialog(null, "Login admin successful!");
//                String info = Main.userManager.GetInfo(username);
//                System.out.println(info);
                User user= userManager.GetUser(username);
                Main.mainScreenAdmin = new MainScreenAdmin(user, userManager);
                this.dispose();
            }
            else JOptionPane.showMessageDialog(null, "Invalid username or password!"); 
            }
        });   
    }  
}

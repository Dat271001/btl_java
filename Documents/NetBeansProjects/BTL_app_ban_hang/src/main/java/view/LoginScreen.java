//package gui;
//
//import controller.UserManager;
//import model.User;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class LoginScreen extends JFrame {
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private JButton loginButton, registerButton;
//    private UserManager userManager;
//
//    public LoginScreen(UserManager userManager) {
//        this.userManager = userManager;
//        setTitle("Login");
//        setSize(500, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JPanel panel = new JPanel(new BorderLayout());
//        JLabel background = new JLabel(new ImageIcon("login_bg.jpg")); // Thêm hình nền
//        background.setLayout(new BorderLayout());
//        panel.add(background);
//
//        JPanel inputPanel = new JPanel();
//        inputPanel.setOpaque(false); // Đặt nền trong suốt
//        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
//        background.add(inputPanel, BorderLayout.CENTER);
//
//        // Username
//        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        userPanel.setOpaque(false);
//        JLabel userLabel = new JLabel("Username:");
//        usernameField = new JTextField(20);
//        userPanel.add(userLabel);
//        userPanel.add(usernameField);
//
//        // Password
//        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        passwordPanel.setOpaque(false);
//        JLabel passwordLabel = new JLabel("Password:");
//        passwordField = new JPasswordField(20);
//        passwordPanel.add(passwordLabel);
//        passwordPanel.add(passwordField);
//
//        // Buttons
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        buttonPanel.setOpaque(false);
//        loginButton = new JButton("Login");
//        registerButton = new JButton("Register");
//        buttonPanel.add(loginButton);
//        buttonPanel.add(registerButton);
//
//        inputPanel.add(userPanel);
//        inputPanel.add(passwordPanel);
//        inputPanel.add(buttonPanel);
//
//        // Action listeners
//        loginButton.addActionListener(e -> {
//            String username = usernameField.getText();
//            String password = new String(passwordField.getPassword());
//            User user = userManager.login(username, password);
//            if (user != null) {
//                JOptionPane.showMessageDialog(this, "Login successful!");
//                new MainScreen(user, userManager).setVisible(true);
//                dispose();
//            } else {
//                JOptionPane.showMessageDialog(this, "Invalid username or password!");
//            }
//        });
//
//        registerButton.addActionListener(e -> {
//            String username = usernameField.getText();
//            String password = new String(passwordField.getPassword());
//            if (userManager.register(username, password)) {
//                JOptionPane.showMessageDialog(this, "Registration successful!");
//            } else {
//                JOptionPane.showMessageDialog(this, "Username already exists!");
//            }
//        });
//
//        add(panel);
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        UserManager userManager = new UserManager();
//        new LoginScreen(userManager);
//    }
//}
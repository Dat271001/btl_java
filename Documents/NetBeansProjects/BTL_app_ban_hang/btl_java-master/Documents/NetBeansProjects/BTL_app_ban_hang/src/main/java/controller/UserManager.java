package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.User;

import java.util.HashMap;
import java.util.Scanner;
public class UserManager {
    private HashMap<String, User> users;
//    ArrayList<User> accList = new ArrayList<>();
    File accountFile = new File(new File("src\\main\\java\\controller\\Accs.txt").getAbsolutePath());
    
    public UserManager() throws FileNotFoundException {
        users = new HashMap<>();
        loadUsersFromFile();
    }

    // Đọc dữ liệu người dùng từ file và lưu vào HashMap
    private void loadUsersFromFile() throws FileNotFoundException {
        Scanner sn = new Scanner(accountFile);
        while(sn.hasNextLine()){
            String tmp = sn.nextLine();
            String[] tmpList = tmp.trim().split("\\s+"); 
            if (tmpList.length >= 3) {  // Đảm bảo có đầy đủ username, password, và balance
                String username = tmpList[0];
                String password = tmpList[1];
                double balance = Double.parseDouble(tmpList[2]);
                users.put(username, new User(username, password, balance));
            }
        }
        sn.close();
    }

    // Cập nhật số dư trong file
    public void updateUserBalanceInFile(User user) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        Scanner sn = new Scanner(accountFile);
        
        // Đọc toàn bộ file và cập nhật số dư của user hiện tại
        while(sn.hasNextLine()){
            String line = sn.nextLine();
            String[] tmpList = line.trim().split("\\s+");
            if (tmpList.length >= 3 && tmpList[0].equals(user.getUsername())) {
                // Nếu đúng tài khoản, cập nhật lại dòng với số dư mới
                lines.add(user.getUsername() + " " + user.getPassword() + " " + user.getBalance());
            } else {
                // Ngược lại, giữ nguyên dòng
                lines.add(line);
            }
        }
        sn.close();

        // Ghi lại nội dung đã cập nhật vào file
        FileWriter writer = new FileWriter(accountFile, false); // false để ghi đè file
        for (String line : lines) {
            writer.write(line + "\n");
        }
        writer.close();
    }
    

    // Cộng số tiền thanh toán vào tài khoản Admin
    public void addToAdminBalance(double amount) throws IOException {
        User adminUser = users.get("Admin");

        if (adminUser != null) {
            // Cộng số tiền vào tài khoản Admin
            double newAdminBalance = adminUser.getBalance() + amount;
            adminUser.setBalance(newAdminBalance);

            // Cập nhật lại toàn bộ dữ liệu người dùng vào file
            updateUserBalanceInFile();
        }
    }

    // Cập nhật lại toàn bộ danh sách vào file
    private void updateUserBalanceInFile() throws IOException {
        FileWriter writer = new FileWriter(accountFile, false);
        for (User u : users.values()) {
            writer.write(u.getUsername() + " " + u.getPassword() + " " + u.getBalance() + "\n");
        }
        writer.close();
    }
    
    // Đăng ký người dùng mới 
    public void AddAccount(String username, String pass) throws IOException{
        if(username.isBlank() && pass.isBlank()) return ;
        FileWriter myWriter = new FileWriter(accountFile,true);
//        accList.add(new User(username, pass,1000.0));
        users.put(username,new User(username, pass,1000.0));
        myWriter.write(String.format("\n%s %s", username, pass));
        myWriter.close();
//        System.out.println("Done");
    }


    // Đăng nhập người dùng
    public boolean PasswordCheck(String username, String pass){
        if(username.isBlank() && pass.isBlank()) return false;
        User user = users.get(username);
        if (user != null && user.getPassword().equals(pass)) {
            return true;
        }
        return false;
    }
    public boolean AdminCheck(String username,String pass){
        if(username.compareTo("Admin")==0&&pass.compareTo("1234")==0) return true;
        return false;
    }
    // Check nguoi dung
    public boolean AccountCheck(String username, String pass){
        if(username.isBlank()) return false;
        User user = users.get(username);
        if (user != null) {
            return true;
        }
        return false;
    }
    
    public User GetUser(String username){
        User user = users.get(username);
        return user;
    }
}




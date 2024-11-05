package controller;

import java.io.*;
import java.util.ArrayList;
import model.User;

import java.util.HashMap;
import java.util.Scanner;
public class UserManager {
    private HashMap<String, User> users;
    File accountFile = new File(new File("src\\main\\java\\files\\Accs.txt").getAbsolutePath());
    
    public UserManager() throws FileNotFoundException {
        users = new HashMap<>();
        Scanner sn = new Scanner(accountFile);
        
        while(sn.hasNextLine()){
            String tmp = sn.nextLine();
            String[] tmpList = tmp.trim().split("\\s+"); 
            if(!tmpList[0].isBlank() && !tmpList[1].isBlank()) 
                if(tmpList[0].compareTo("Admin")==0&&tmpList[1].compareTo("1234")==0) users.put(tmpList[0], new User(tmpList[0], tmpList[1],Double.parseDouble(tmpList[2])));
                else users.put(tmpList[0], new User(tmpList[0], tmpList[1],Double.parseDouble(tmpList[2])));
        }
        sn.close();
    }
    
    // Đăng ký người dùng mới 
    public void AddAccount(String username, String pass) throws IOException{
        if(username.isBlank() && pass.isBlank() ) return ;
        FileWriter myWriter = new FileWriter(accountFile,true);
//        accList.add(new User(username, pass,1000.0));
        users.put(username,new User(username, pass,0.0));
        myWriter.write(String.format("\n%s %s %.2f", username, pass, 0.0));
        myWriter.close();
//        System.out.println("Done");
    }


    // Đăng nhập người dùng
    public boolean PasswordCheck(String username, String pass){
        if(username.isBlank() && pass.isBlank() && pass.length()<6) return false;
        User user = users.get(username);
        if (user != null && user.getPassword().equals(pass)) {
            return true;
        }
        return false;
    }
    public boolean AdminCheck(String username,String pass){
        if(username.compareTo("Admin")==0&&pass.compareTo("123456")==0) return true;
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




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
        Scanner sn = new Scanner(accountFile);
        
        while(sn.hasNextLine()){
            String tmp = sn.nextLine();
            String[] tmpList = tmp.trim().split("\\s+"); 
//            User tmpAcc = new User(tmpList[0], tmpList[1], 1000.0);
//            accList.add(tmpAcc);
            if(!tmpList[0].isBlank() && !tmpList[1].isBlank()) users.put(tmpList[0], new User(tmpList[0], tmpList[1], 1000.0));
        }
        sn.close();
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


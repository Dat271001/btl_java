/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Accounts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class LoginProcess {
    ArrayList<Account> accList = new ArrayList<>();
    File accountFile = new File("D:\\New Folder\\BTL_Java\\src\\Accounts\\Accs.txt");
    
    public LoginProcess() throws FileNotFoundException{ 
        Scanner sn = new Scanner(accountFile);
        while(sn.hasNextLine()){
            String tmp = sn.nextLine();
            String[] tmpList = tmp.trim().split("\\s+");
//            System.out.println(tmp);      
            if(tmpList[2].equals("B") || tmpList[2].equals("S")){
                Account tmpAcc = new Account(tmpList[0], tmpList[1], tmpList[2]);
                accList.add(tmpAcc);
            }
        }
        sn.close();
    }
    
    public void ListAccounts(){
        for(Account x: accList) System.out.println("U:" + x.getUsername() + " P:" + x.getPassword() + " T:" + x.getAccountType());
    }
    public void AddAccount(String username, String pass, String type) throws IOException{
        FileWriter myWriter = new FileWriter(accountFile,true);
        accList.add(new Account(username, pass, type));
        myWriter.write(String.format("%s %s %s\n", username, pass, type));
        myWriter.close();
        
        System.out.println("Done");
    }
    
    public boolean PasswordCheck(String username, String pass){
        for(Account x: accList){
            if(x.getUsername().equals(username) && x.getPassword().equals(pass)) return true;
        }
        return false;
    }
    
    public String GetInfo(String username){
        String tmp="";
        for(Account x: accList){
            if(x.getUsername().equals(username)){
                tmp += x.getUsername() + "  ";
                if(x.getAccountType().equals("B")) tmp += "Buyer";
                else if(x.getAccountType().equals("S")) tmp += "Seller";
                break;
            }
        }
        return tmp;
    }
}

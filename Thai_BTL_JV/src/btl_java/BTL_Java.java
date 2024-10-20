/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package btl_java;

import Accounts.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import loginGUI.*;
import MainUI.*;
//import loginGUI.LoginForm;

/**
 *
 * @author PC
 */
public class BTL_Java {

    /**
     * @param args the command line arguments
     */
    public static LoginForm login;
    public static LoginProcess accountProcess;
    public static MainScreen mainScreen;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        login = new LoginForm();
        accountProcess = new LoginProcess();
//        accountProcess.ListAccounts();
//        accountProcess.AddAccount("Test", "Test", "B");
//        mainScreen = new MainScreen();
    }
    
}

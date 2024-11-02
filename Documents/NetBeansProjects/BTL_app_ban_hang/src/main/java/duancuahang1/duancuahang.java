/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package duancuahang1;

import controller.UserManager;
import gui.*;
import java.io.FileNotFoundException;
import model.User;

/**
 *
 * @author Dell
 */
public class duancuahang {

    /**
     * @param args the command line arguments
     */
    public static LoginForm login;
    public static UserManager userManager;
    public static MainScreen mainScreen;
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
       login = new LoginForm();
        userManager = new UserManager();
        // mainScreen = new MainScreen(new User("test","test",1000.0), userManager);
    }
    
}

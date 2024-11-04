/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package duancuahang1;

import view.LoginForm;
import view.MainScreenAdmin;
import view.MainScreen;
import controller.UserManager;
import view.LoadingScreen;
import static view.LoadingScreen.jProgressBar1;
import static view.LoadingScreen.percentage;
import java.io.FileNotFoundException;
import model.User;

public class duancuahang {

    public static LoginForm login;
    public static UserManager userManager;
    public static MainScreen mainScreen;
    public static MainScreenAdmin mainScreenAdmin;
    public static void main(String[] args) throws FileNotFoundException {
        
        LoadingScreen loading = new LoadingScreen();
        loading.setVisible(true);
        try{
            for(int i=0; i<=100; i++){
                Thread.sleep(10);
                percentage.setText(Integer.toString(i) + "%");
                jProgressBar1.setValue(i);

                if(i==100){
                    loading.dispose();
                    
                }
            }

        } catch(Exception e){

        }
       
        login = new LoginForm();
        userManager = new UserManager();
    }
    
}

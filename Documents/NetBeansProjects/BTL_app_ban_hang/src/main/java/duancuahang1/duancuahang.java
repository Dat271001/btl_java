/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package duancuahang1;

import gui.LoginForm;
import gui.MainScreenAdmin;
import gui.MainScreen;
import controller.UserManager;
import gui.LoadingScreen;
import static gui.LoadingScreen.jProgressBar1;
import static gui.LoadingScreen.percentage;
import java.io.FileNotFoundException;
import model.User;

public class duancuahang {

    public static LoginForm login;
    public static UserManager userManager;
    public static MainScreen mainScreen;
    public static MainScreenAdmin mainScreenAdmin;
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
//       login = new LoginForm();
//        userManager = new UserManager();
//         mainScreen = new MainScreen(new User("test","test",1000.0), userManager);
//         mainScreenAdmin = new MainScreenAdmin(new User("Admin","1234",1000.0), userManager);
        
        
//        //Neu loi cmt tu day >> dong 47
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
        
        // END o day
        
        login = new LoginForm();
        userManager = new UserManager();
    }
    
}

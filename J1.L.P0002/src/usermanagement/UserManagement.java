/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermanagement;

import account.AccountList;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import util.MyToys;

/**
 *
 * @author LP D
 */
public class UserManagement {
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        AccountList AccList = new AccountList();
        AccList.addFromFile("user.txt");
        int choice = 0;
        do {
            System.out.println( "1. Create user account.\n" +
                                "2. Check exits user.\n" +
                                "3. Search user information by name.\n" +
                                "4. Update user:\n" +
                                "   4.1. Update user.\n" +
                                "   4.2. Delete user.\n" +
                                "5. Save account to file.\n" +
                                "6. Print list user from file.\n" +
                                "Others- Quit Program.");
            choice = MyToys.getInteger("Your choice: ", "Input 1-6 or others to exit the Program. ");
            switch(choice) {
                case 1: AccList.createAccount();
                    break;
                case 2: AccList.checkUser();
                    break;
                case 3: AccList.findByName();
                    break;
                case 4: AccList.updateOrDelete();
                    break;
                case 5: AccList.saveToFile();
                    break;
                case 6: AccList.printFromFile();
                    break;
            }
        } while (0 < choice && choice < 7);
    }
    
}

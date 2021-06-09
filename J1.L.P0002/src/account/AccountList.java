/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import util.MyToys;

/**
 *
 * @author LP D
 */
public class AccountList extends ArrayList<Account> {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String confirm;
    private String phone;
    private String email;

    public void addFromFile(String fileUser) throws IOException {
        BufferedReader bf = null;
        try {
            Reader r = new FileReader(fileUser);
            bf = new BufferedReader(r);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ";");
                username = stk.nextToken();
                firstName = stk.nextToken();
                lastName = stk.nextToken();
                password = stk.nextToken();
                phone = stk.nextToken();
                email = stk.nextToken();
                Account acc = new Account(username, firstName, lastName, password, phone, email);
                this.add(acc);
            }
        } finally {
            if (bf != null) {
                bf.close();
            }
        }
    }

    public void createAccount() {
        String keep;
        int compare;
        do {
            do {
                username = MyToys.getString("Input username: ", "The format of username is a-z & 0-9 & at least 5 characters. ", "^[a-z0-9._]{5,50}$");
                compare = findUsernameV2(username);
                if (compare >= 0) {
                    System.out.println("This username already exists, input another username! ");
                }
            } while (compare != -1);
            firstName = MyToys.getString("Input first name: ", "The format of first name is a-z & A-Z. ", "[a-z A-Z]{1,50}$");
            lastName = MyToys.getString("Input last name: ", "The format of last name is a-z & A-Z. ", "[a-z A-Z]{1,50}$");
            password = MyToys.getString("Input password: ", "The format of password is a-z & A-Z & 0-9 & at least 6 characters. ", "^[\\w._@]{6,50}$");
            do {
                confirm = MyToys.getString("Confirm password: ", "The format of password is a-z & A-Z & 0-9 & at least 6 characters. ", "^[\\w._@]{6,50}$");
                if (!confirm.equals(password)) {
                    System.out.println("Password is not the same.");
                }
            } while (!confirm.equals(password));
            phone = MyToys.getString("Input phone number: ", "The phone number must be 10 digits long. ", "^[0-9]{10}$");
            email = MyToys.getString("Input email: ", "The format (Ex: abc@gmail.com). ", "^[\\w._]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]+\\.[\\w])$");
            this.add(new Account(username, firstName, lastName, password, confirm, phone, email));
            Collections.sort(this);
            System.out.println("Create Account Success");
            keep = MyToys.getString("Do you want to continue (Y/N): ",
                    "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
            if (keep.equalsIgnoreCase("N")) {
                break;
            }
        } while (keep.equalsIgnoreCase("Y"));
    }

    public void checkUser() {
        String keep;
        int compare;
        do {
            username = MyToys.getString("Input username: ", "The format of username is a-z & 0-9 & at least 5 characters. ", "^[a-z0-9._]{5,50}$");
            compare = findUsernameV2(username);
            if (compare < 0) {
                System.out.println("No User found!");
            } else {
                System.out.println("username exists in the user.txt");
            }
            keep = MyToys.getString("Do you want to continue (Y/N): ",
                    "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
            if (keep.equalsIgnoreCase("N")) {
                break;
            }
        } while (keep.equalsIgnoreCase("Y"));
    }

    public void findByName() {
        String keep;
        int compare1, compare2;
        String name;
        boolean check = false;
        do {
            name = MyToys.getString("Input the search name: ", "The format of name is a-z & A-Z. ", "[a-z A-Z]{1,50}$");
            for (int i = 0; i < this.size(); i++) {
                compare1 = this.get(i).getFirstName().indexOf(name);
                compare2 = this.get(i).getLastName().indexOf(name);
                if (compare1 >= 0 || compare2 >= 0) {
                    System.out.println(this.get(i));
                    check = true;
                }
            }
            if (check == false)
                System.out.println("Have no any user");
            keep = MyToys.getString("Do you want to continue (Y/N): ",
                    "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
            if (keep.equalsIgnoreCase("N")) {
                break;
            }
        } while (keep.equalsIgnoreCase("Y"));
    }

    public void updateAccount() {
        String tmpFirstName, tmpLastName, tmpPassword, tmpConfirm, tmpPhone, tmpEmail;
        Account x = findUsername(username);
        System.out.println("Before the update:");
        System.out.println(x);
        tmpFirstName = MyToys.getString2("Input first name: ", "The format of first name is a-z & A-Z. ", "[a-z A-Z]{1,50}$");
        if (tmpFirstName.isEmpty()) {
            x.getFirstName();
        } else {
            x.setFirstName(tmpFirstName);
        }
        tmpLastName = MyToys.getString2("Input last name: ", "The format of last name is a-z & A-Z. ", "[a-z A-Z]{1,50}$");
        if (tmpLastName.isEmpty()) {
            x.getLastName();
        } else {
            x.setLastName(tmpLastName);
        }
        tmpPassword = MyToys.getString2("Input password: ", "The format of password is a-z & A-Z & 0-9 & at least 6 characters. ", "^[\\w._@]{6,50}$");
        if (tmpPassword.isEmpty()) {
            x.getPassword();
        } else {
            x.setPassword(tmpPassword);
        }
        do {
            tmpConfirm = MyToys.getString2("Confirm password: ", "The format of password is a-z & A-Z & 0-9 & at least 6 characters. ", "^[\\w._@]{6,50}$");
            if (tmpConfirm.isEmpty()) {
                x.getConfirm();
            } else {
                x.setConfirm(tmpConfirm);
            }
            if (!tmpConfirm.equals(tmpPassword)) {
                System.out.println("Password is not the same.");
            }
        } while (!(tmpConfirm.equals(tmpPassword) || tmpConfirm.isEmpty()));
        phone = MyToys.getString2("Input phone number: ", "The phone number must be 10 digits long. ", "^[0-9]{10}$");
        email = MyToys.getString2("Input email: ", "The format (Ex: abc@gmail.com). ", "^[\\w._]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]+\\.[\\w])$");
        System.out.println("Update successful");
    }

    public void deleteAccount() {
        Account x = findUsername(username);
        System.out.println("Before the delete");
        System.out.println(x);
        String del = MyToys.getString("Are you sure you want to delete? (Y/N): ",
                "Choose Y to delete, N to return main screen. ", "^[YyNn]{1}$");
        if (del.equalsIgnoreCase("Y")) {
            this.remove(x);
            System.out.println("Deleted successfully");
        } else {
            System.out.println("Delete failed");
        }
    }
    
    public void updateOrDelete() {
        Scanner sc = new Scanner(System.in);
        String keep;
        String choice;
        boolean check = false;
        do {
            username = MyToys.getString("Input username: ", "The format of username is a-z & 0-9 & at least 5 characters. ", "^[a-z0-9._]{5,50}$");
            password = MyToys.getString("Input password: ", "The format of password is a-z & A-Z & 0-9 & at least 6 characters. ", "^^[\\w._@]{6,50}$");
//            password = sc.nextLine();
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getUsername().equals(username) && this.get(i).getPassword().equals(password)) {
                    System.out.println("Logged in successfully.");
                    choice = MyToys.getFormat("Do you want to update (4.1) or delete (4.2) user: ",
                            "update (4.1) or delete (4.2), please. ", "4.1", "4.2");
                    if (choice.equalsIgnoreCase("4.1")) {
                        updateAccount();
                    }
                    if (choice.equalsIgnoreCase("4.2")) {
                        deleteAccount();
                    }
                check = true;   
                }
            }
            if (check == false)
                  System.out.println("User account or password incorrect.");
            keep = MyToys.getString("Do you want to continue (Y/N): ",
                    "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
            if (keep.equalsIgnoreCase("N")) {
                break;
            }
        } while (keep.equalsIgnoreCase("Y"));
    }

    public void saveToFile() throws IOException, NoSuchAlgorithmException {
        String keep;
        do {
            File file = new File("user.txt");
            if (!file.exists()) {
                file.createNewFile();
            } 
            BufferedWriter bf = null;
            try {
                Writer w = new FileWriter("user.txt");
                bf = new BufferedWriter(w);
                for (Account x : this) {
//                    MessageDigest md = MessageDigest.getInstance("SHA-256");
//                    md.update(x.getPassword().getBytes());
//                    byte[] digest = md.digest();
//                    StringBuffer sb = new StringBuffer();
//                    for (byte b : digest) {
//                        sb.append(String.format("%02x", b & 0xff));
//                    }
                    String s = x.getUsername() + ";" + x.getFirstName() + ";" + x.getLastName() + ";" + x.getPassword() + ";" + x.getPhone() + ";" + x.getEmail() + "\n";
                    bf.write(s);
                }
            } finally {
                if (bf != null) {
                    bf.close();
                }
            }
            System.out.println("Save to file successfully!");
            keep = MyToys.getString("Do you want to continue (Y/N): ",
                    "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
            if (keep.equalsIgnoreCase("N")) {
                break;
            }
        } while (keep.equalsIgnoreCase("Y"));
    }
    
    public void printFromFile() {
        for (int i = 0; i < size(); i++) {
            System.out.println("--------------------------------------------\n"+
                               "username: " + get(i).getUsername() + "\n" + 
                               "fullName: " + get(i).getLastName() + " " + get(i).getFirstName() + "\n" + 
                               "password: " + get(i).getPassword() + "\n" +
                               "phone: " + get(i).getPhone() + "\n" + 
                               "email: " + get(i).getEmail() + "\n");
        }     
    }
    
    public int findUsernameV2(String username) {
        if (this.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public Account findUsername(String username) {
        if (this.isEmpty()) {
            return null;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getUsername().equals(username)) {
                return this.get(i);
            }
        }
        return null;
    }    
}

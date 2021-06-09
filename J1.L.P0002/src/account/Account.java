/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;




/**
 *
 * @author LP D
 */
public class Account implements Comparable<Account>{

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String confirm;
    private String phone;
    private String email;

    public Account(String username, String firstName, String lastName, String password, String confirm, String phone, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirm = confirm;
        this.phone = phone;
        this.email = email;
    }

    Account(String username, String firstName, String lastName, String password, String phone, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
//        return "username: " + username + ", fullName: " + lastName + " " + firstName + ", password: " + password + ", phone: " + phone + ", email: " + email;
        return "--------------------------------------------\n"+
               "username: " + username + "\n" + 
               "fullName: " + lastName + " " + firstName + "\n" + 
               "password: " + password + "\n" +
               "phone: " + phone + "\n" + 
               "email: " + email + "\n";
    }

    @Override
    public int compareTo(Account o) {
        return firstName.compareTo(o.firstName);
    }
}

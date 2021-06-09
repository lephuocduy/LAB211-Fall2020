/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.util.ArrayList;
import java.util.Scanner;
import util.MyToys;

/**
 *
 * @author LP D
 */
public class StudentList extends ArrayList<Student> {
    
    private final Scanner sc = new Scanner (System.in);
    private String studentId;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String email;
    private String phoneNumber;    
    
    public void addStudent() { 
        int compareId;
        String keep;
        do {
            do {
                studentId = MyToys.getString("Input student id(SEXXXXXX): ", 
                    "The format of id is SEXXXXXX (X stands for a digit). ", "^[SE]{2}\\d{6}$");
                compareId = findStudentIdV2(studentId);
                if (compareId >= 0) 
                    System.out.println("This id already exists, input another id! ");
            } while (compareId != -1);
            firstName = MyToys.getString("Input first name: ", "The format of first name is a-z & A-Z. ", "[a-z A-Z]{1,50}$"); 
            lastName = MyToys.getString("Input last name: ", "The format of last name is a-z & A-Z. ", "[a-z A-Z]{1,50}$");
            gender = MyToys.getFormat("Input 'male' or 'female' gender: ", "The format is 'male' or 'female'. ", "male", "female");
            int t;
            do {
                dob = MyToys.getString("Input date of birth(dd/mm/yyyy): ", "The format dd/mm/yyyy. ", "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");                
                t = MyToys.toDate(dob);
                if (t < 0)
                    System.out.println("Date is invalid!");
            } while (t < 0);
            email = MyToys.getString("Input email: ", "The format (Ex: abc@gmail.com). ", "^[\\w._]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]+\\.[\\w])$"); 
            phoneNumber = MyToys.getString("Input phone number: ", "Phone numbers are 10 to 12 numbers long. ", "^[0-9]{10,12}$");
            this.add(new Student(studentId, firstName, lastName, gender, dob, email, phoneNumber));
            keep = MyToys.getString("Do you want to continue (Y/N): ", 
                            "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
            if (keep.equalsIgnoreCase("N"))                
                break;
        } while (keep.equalsIgnoreCase("Y"));
    }

    public void updateStudent() {
        String tmpFirstName, tmpLastName, tmpGender, tmpDob, tmpEmail, tmpPhoneNumber;
        Student x = findStudentId(studentId);        
        System.out.println("Before the update");        
        System.out.println(x);
        tmpFirstName = MyToys.getString2("Input first name: ", "The format of first name is a-z & A-Z. ", "[a-z A-Z]{1,50}$");
        if (tmpFirstName.isEmpty())
            x.getFirstName();
        else
            x.setFirstName(tmpFirstName);
        tmpLastName = MyToys.getString2("Input last name: ", "The format of last name is a-z & A-Z. ", "[a-z A-Z]{1,50}$");
        if (tmpLastName.isEmpty())
            x.getLastName();
        else
            x.setLastName(tmpLastName);
        tmpGender = MyToys.getFormat2("Input 'male' or 'female' gender: ", "The format is 'male' or 'female'. ", "male", "female");
        if (tmpGender.isEmpty())
            x.getGender();
        else
            x.setGender(tmpGender);
        int t;
        do {
            tmpDob = MyToys.getString2("Input date of birth(dd/mm/yyyy): ", "The format dd/mm/yyyy. ", "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
            if (tmpDob.isEmpty()) {
                x.getDob();
                break;
            }
            else
                x.setDob(tmpDob);
            t = MyToys.toDate(tmpDob);
            if (t < 0)
                System.out.println("Date is invalid!");
        } while (t < 0);
        tmpEmail = MyToys.getString2("Input email: ", "The format (Ex: abc@gmail.com). ", "^[\\w._]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]+\\.[\\w])$"); 
        if (tmpEmail.isEmpty())
            x.getEmail();
        else
            x.setEmail(tmpEmail);
        tmpPhoneNumber = MyToys.getString2("Input phone number: ", "Phone numbers are 10 to 12 numbers long. ", "^[0-9]{10,12}$");
        if (tmpPhoneNumber.isEmpty())
            x.getPhoneNumber();
        else
            x.setPhoneNumber(tmpPhoneNumber);
        System.out.println("Update successful");
    }
    
    public void deleteStudent() {
        Student x = findStudentId(studentId);
        System.out.println("Before the delete");        
        System.out.println(x);
        String del = MyToys.getString("Are you sure you want to delete? (Y/N): ", 
                            "Choose Y to delete, N to return main screen. ", "^[YyNn]{1}$");
        if (del.equalsIgnoreCase("Y")) {                
            this.remove(x);
            System.out.println("Deleted successfully");
        }
        else
            System.out.println("Delete failed");           
    }
    
    public void updateOrDeleteStudent() {
        String choice;
        String keep;
        do {
            studentId = MyToys.getString("Input student id(SEXXXXXX): ", 
                        "The format of id is SEXXXXXX (X stands for a digit). ", "^[SE]{2}\\d{6}$");
            Student x = findStudentId(studentId);
            if (x == null )                         
                System.out.println("Not found");
            else {            
                choice = MyToys.getFormat("Do you want to update (2.1) or delete (2.2) student: ", 
                        "update (2.1) or delete (2.2), please. ", "2.1", "2.2");
                if (choice.equalsIgnoreCase("2.1")) {                
                    updateStudent();                                            
                }
                if (choice.equalsIgnoreCase("2.2")) {
                    deleteStudent();
                }
            }
            keep = MyToys.getString("Do you want to continue (Y/N): ", 
                          "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
            if (keep.equalsIgnoreCase("N"))                
                break;
        } while (keep.equalsIgnoreCase("Y"));
    }
    
    public Student findStudentId(String id) {
        if (id.isEmpty())
            return null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getStudentId().equalsIgnoreCase(id))
                return this.get(i);
        }
        return null;        
    }
    
    public int findStudentIdV2(String id) {
        if (this.isEmpty())
            return -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getStudentId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import grade.GradeList;
import student.StudentList;
import subject.SubjectList;
import util.MyToys;

/**
 *
 * @author LP D
 */
public class StudentManagement {
    
    public static void main(String[] args) {
        StudentList stdList = new StudentList();
        SubjectList subList = new SubjectList();       
        GradeList graList = new GradeList(stdList, subList);
        int choice;
        do {
            System.out.println( "1. Add new student\n" +
                                "2. Update Student\n" +
                                "   2.1 Update Student\n" +
                                "   2.2 Delete Student\n" +
                                "3. Add new Subject\n" +
                                "4. Update Subject\n" +
                                "   4.1 Update Subject\n" +
                                "   4.2 Delete Subject\n" +
                                "5. Enter Grade\n" +
                                "6. Display Student Grade Report\n" +
                                "7. Display Subject Grade Report\n" +
                                "Others - Quit Program");
            choice = MyToys.getInteger("Your choice: ", "Input 1-7 or others to exit the Program. ");
            switch(choice) {
                case 1: stdList.addStudent();
                    break;
                case 2: stdList.updateOrDeleteStudent();
                    break;
                case 3: subList.addSubject();
                    break;
                case 4: subList.updateOrDeleteSubject();
                    break;
                case 5: graList.addGrade();
                    break;
                case 6: graList.studentReport();
                    break;
                case 7: graList.subjectReport();
                    break;
            }
        } while (0 < choice && choice < 8);
    }
}    


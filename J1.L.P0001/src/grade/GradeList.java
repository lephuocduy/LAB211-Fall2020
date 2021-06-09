/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grade;

import java.util.ArrayList;
import java.util.Collections;
import student.Student;
import student.StudentList;
import subject.Subject;
import subject.SubjectList;
import util.MyToys;

/**
 *
 * @author LP D
 */
public class GradeList extends ArrayList<Grade> {
    
    private Student std;
    private StudentList stdList;
    private Subject sub;
    private SubjectList subList;
    private double labs;
    private double test;
    private double fe;
    private String idStd;
    private String idSub;
    
    public GradeList(StudentList stdList, SubjectList subList) {
        this.stdList = stdList;
        this.subList = subList;
    }

    public void addGrade() {
        String keep;
        String gra;
        int compareIdStd = -1; 
        int compareIdSub = -1;
        int compareGrade = -1;
        do {            
            do {
                idStd = MyToys.getString("Input student id(SEXXXXXX): ",
                        "The format of id is SEXXXXXX (X stands for a digit). ", "^[SE]{2}\\d{6}$");
                compareIdStd = findStudentIdG(idStd);
                if (compareIdStd < 0) {
                    System.out.println("This id does not exists, input another id! ");
                }
            } while (compareIdStd < 0);
            do {
                idSub = MyToys.getString("Input subject id(ABCXXX): ",
                        "The format of id is ABCXXX(3 letters and 3 numbers). ", "^[A-Z]{3}\\d{3}$");
                compareIdSub = findSubjectIdG(idSub);
                if (compareIdSub < 0) {
                    System.out.println("This id does not exists, input another id! ");
                }
            } while (compareIdSub < 0);
            compareGrade = findBothIds(idStd, idSub);
            if (compareGrade >= 0) {
                gra = MyToys.getString("This grade existed, do you want to overwrite it? (Y/N): ",
                        "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
                if (gra.equalsIgnoreCase("N"))
                    break;
                else
                    this.remove(compareGrade);
                }
            labs = MyToys.getPoint("Input labs: ", "score from 0 to 10. ");
            test = MyToys.getPoint("Input progress tests: ", "score from 0 to 10. ");
            fe = MyToys.getPoint("Input final exam: ", "score from 0 to 10. ");
            this.add(new Grade(stdList.get(compareIdStd), subList.get(compareIdSub), labs, test, fe));
            System.out.println("Successfully added grade");
            keep = MyToys.getString("Do you want to continue (Y/N): ",
                    "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
            if (keep.equalsIgnoreCase("N")) {
                break;
            }
        } while (keep.equalsIgnoreCase("Y"));        
    }

    public void studentReport() {
        int compareId;
        String fullName;
        String keep;
        do {
            do {
                idStd = MyToys.getString("Input student id(SEXXXXXX): ",
                        "The format of id is SEXXXXXX (X stands for a digit). ", "^[SE]{2}\\d{6}$");
                compareId = findStudentIdG(idStd);
                if (compareId < 0) {
                    System.out.println("This id does not exists, input another id! ");
                }
            } while (compareId < 0);
            System.out.println("Student ID:" + idStd);
            Student stdn = this.get(compareId).std;
            fullName = stdn.getLastName() + " " + stdn.getFirstName();
            System.out.println("Student name:" + fullName);
            if (this.isEmpty()) {
                System.out.println("Nothing to report");
            }
            System.out.println("|++No++|+++++++Subject name+++++++|++Avegare mark++|++Status++|");
            int count = 1;
            Collections.sort(this);
            for (Grade d : this) {
                if (idStd.equals(d.std.studentId)) {
                double total = d.average();
                String status = (total >= 5.0) ? "PASS" : "NOT PASS";
                String subName = d.sub.subjectName;
                System.out.printf("|%-6d|%-26s|%-16.1f|%-10s|\n", count, subName, total, status);
                count ++;
                }
            }
            keep = MyToys.getString("Do you want to continue (Y/N): ",
                    "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
        } while (keep.equalsIgnoreCase("Y"));
    }

    public void subjectReport() {
        int compareId;
        String keep;
        String fullName;
        do {
            do {
                idSub = MyToys.getString("Input subject id(ABCXXX): ",
                        "The format of id is ABCXXX(3 letters and 3 numbers). ", "^[A-Z]{3}\\d{3}$");
                compareId = findSubjectIdG(idSub);
                if (compareId < 0) {
                    System.out.println("This id does not exists, input another id! ");
                }
            } while (compareId < 0);
            System.out.println("Subject ID:" + idSub);
            Subject subn = this.get(compareId).sub;
            System.out.println("Subject name:" + subn.subjectName);
            if (this.isEmpty()) {
                System.out.println("Nothing to report");
            }
            System.out.println("|++Student ID++|+++++++Student name+++++++|++Avegare mark++|++Status++|");
            Collections.sort(this);
            for (Grade d : this) {
                if (idSub.equals(d.sub.subjectId)) {
                std = stdList.findStudentId(idStd);
                fullName = d.std.getLastName() + " " + d.std.getFirstName();
                double total = d.average();
                String status = (total >= 5.0) ? "PASS" : "NOT PASS";
                System.out.printf("|%-14s|%-26s|%-16.1f|%-10s|\n", d.std.studentId, fullName, total, status);
                }
            }
            keep = MyToys.getString("Do you want to continue (Y/N): ",
                    "Choose Y to continue, N to return main screen. ", "^[YyNn]{1}$");
        } while (keep.equalsIgnoreCase("Y"));
    }
    
    public int findBothIds(String stdId, String subId) {
        int posStd = findStudentIdG(stdId);
        int posSub = findSubjectIdG(subId);
        if (posStd >= 0 && posSub >= 0) {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getStd().getStudentId().equals(stdId) && this.get(i).getSub().getSubjectId().equals(subId)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public int findStudentIdG(String stdId) {
        for (int i = 0; i < stdList.size(); i++) {
            if (stdList.get(i).getStudentId().equals(stdId)) {
                return i;
            }
        }
        return -1;
    }

    public int findSubjectIdG(String subId) {
        for (int i = 0; i < subList.size(); i++) {
            if (subList.get(i).getSubjectId().equals(subId)) {
                return i;
            }
        }
        return -1;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grade;

import student.Student;
import subject.Subject;

/**
 *
 * @author LP D
 */
public class Grade implements Comparable<Grade> {
    
    public Student std;
    public Subject sub;
    private double labs;
    private double test; 
    private double fe;

    public Grade(Student std, Subject sub, double labs, double test, double fe) {
        this.std = std;
        this.sub = sub;
        this.labs = labs;
        this.test = test;
        this.fe = fe;
    }

    public Student getStd() {
        return std;
    }

    public void setStd(Student std) {
        this.std = std;
    }

    public Subject getSub() {
        return sub;
    }

    public void setSub(Subject sub) {
        this.sub = sub;
    }

    public double getLabs() {
        return labs;
    }

    public void setLabs(double labs) {
        this.labs = labs;
    }

    public double getTest() {
        return test;
    }

    public void setTest(double test) {
        this.test = test;
    }

    public double getFe() {
        return fe;
    }

    public void setFe(double fe) {
        this.fe = fe;
    }
    
    public double average () { 
        return 0.3*labs + 0.3*test + 0.4*fe; 
    }

    @Override
    public int compareTo(Grade o) {
        return sub.getSubjectName().compareTo(o.sub.getSubjectName()) + std.getLastName().compareTo(o.std.getLastName());
    }  
    
}

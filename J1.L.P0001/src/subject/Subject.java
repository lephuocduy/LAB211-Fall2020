/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

/**
 *
 * @author LP D
 */
public class Subject {
    
    public String subjectId;
    public String subjectName;
    private String credit;
        
    public Subject(String subjectId, String subjectName, String credit) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credit = credit;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "subjectId: " + subjectId + ", subjectName: " + subjectName + ", credit: " + credit;
    }      
    
}

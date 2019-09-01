package Utilities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "student")
public class Student {

    private int studentNumber;
    private String fullName;
    private String homeAddress;

    public Student() {
    }

    public Student(int sn, String name, String address) {
        studentNumber = sn;
        fullName = name;
        homeAddress = address;
    }

    public void setStudentNumber(int sn) {
        studentNumber = sn;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setFullName(String name) {
        fullName = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setHomeAddress(String address) {
        homeAddress = address;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    @Override
    public String toString() {
        return "Student Number:" + this.studentNumber
                + ", " + "Student Full Name: "
                + this.fullName + ", "
                + "Student Home Address: "
                + this.homeAddress + ".\n";
    }
}

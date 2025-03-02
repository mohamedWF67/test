package com.mycompany.test;

public class Teacher extends User{

    String teacherName;
    String qualification;
    int salary;
    int mobileNo;
    String Address;

    public Teacher(String username, String password, String teacherName, String qualification, int salary, int mobileNo, String address) {
        super(username, password);
        this.teacherName = teacherName;
        this.qualification = qualification;
        this.salary = salary;
        this.mobileNo = mobileNo;
        Address = address;
    }

    public Teacher(String username, String password, int type, String teacherName, String qualification, int salary, int mobileNo, String address) {
        super(username, password, type);
        this.teacherName = teacherName;
        this.qualification = qualification;
        this.salary = salary;
        this.mobileNo = mobileNo;
        Address = address;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(int mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherName='" + teacherName + '\'' +
                ",Email='" + super.getUsername() + '\'' +
                ",Password='" + super.getPassword() + '\'' +
                ", qualification='" + qualification + '\'' +
                ", salary=" + salary +
                ", mobileNo=" + mobileNo +
                ", Address='" + Address + '\'' +
                '}';
    }
    @Override
    public String toFormattedString() {
        return "Teacher{" + "@TeacherName=" + teacherName + ", @Email=" + super.getUsername() + ", @Password=" + super.getPassword() + ", @Qualification=" + qualification + ", @Salary=" + salary + ", @MobileNo=" + mobileNo + ", @Address=" + Address + '}';
    }
}

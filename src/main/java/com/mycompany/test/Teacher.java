package com.mycompany.test;

public class Teacher extends User{

    String teacherName;
    String qualification;
    int salary;
    int mobileNo;
    String Address;

    public Teacher(String Email, String password, String teacherName, String qualification, int salary, int mobileNo, String address) {
        super(Email, password);
        this.teacherName = teacherName;
        this.qualification = qualification;
        this.salary = salary;
        this.mobileNo = mobileNo;
        Address = address;
    }

    public Teacher(int ID,String Email, String password, String teacherName, String qualification, int salary, int mobileNo, String address) {
        super(ID,Email, password);
        this.teacherName = teacherName;
        this.qualification = qualification;
        this.salary = salary;
        this.mobileNo = mobileNo;
        Address = address;
    }

    public Teacher(String Email, String password, int type, String teacherName, String qualification, int salary, int mobileNo, String address) {
        super(Email, password, type);
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
                "teacher_ID'" + super.getID() + "\'" +
                ",teacherName='" + teacherName + '\'' +
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
        return "Teacher{" + "@Teacher_ID="+ super.getID() + ", @TeacherName=" + teacherName + ", @Email=" + super.getUsername() + ", @Password=" + super.getPassword() + ", @Qualification=" + qualification + ", @Salary=" + salary + ", @MobileNo=" + mobileNo + ", @Address=" + Address + "}@end";
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author mohamed waleed
 */
public class Creds {
    private static ArrayList<User> users;
    private static ArrayList<Teacher> teachers;
    private static int currentID;

    public Creds() {
        users = new ArrayList<>();
        teachers = new ArrayList<>();
    }

    public static void setUsers(ArrayList<Object> objects) {
        Creds.users = new ArrayList<>();
        for (Object object : objects) {
            if (object instanceof User) {
                if (object instanceof Teacher) {
                    addUser((Teacher) object);
                    System.out.println("Teacher added");
                }
                addUser((User)object);
                System.out.println("added user: " + object);
            }
        }
    }

    public static int getCurrentID() {
        return currentID;
    }

    public static void setCurrentID(int currentID) {
        Creds.currentID = currentID;
    }

    public static User getCurrentTeacher() {
        for (Teacher teacher : teachers) {
            if (teacher.getID() == currentID) {
                return teacher;
            }
        }
        return null;
    }

    public static String addUser(User user){
        if (!users.contains(user)) {
            users.add(user);
            return "User added";
        }
        return "User already exists";
    }

    public static String addUser(Teacher teacher){
        if (!users.contains(teacher)||teachers.contains(teacher)) {
            users.add(teacher);
            teachers.add(teacher);
            return "Teacher added";
        }
        return "Teacher already exists";
    }
    public static String removeUser(User user){
        users.remove(user);
        if (user instanceof Teacher){
            teachers.remove((Teacher)user);
        }
        return "User deleted";
    }
    public static String getPassword(String username){
        User user = getUser(username);
        if (user != null){
            return user.getPassword();
        }else {
            return null;
        }
    }
    public static User getUser(String username){
        for(User user : users){
            if(user.getUsername().equals(username)){
                if (Test.isDebug()){
                    System.out.println("found user = " + user.getUsername());
                }
                return user;
            }
        }
        return null;
    }
    public static void printUsers(){
        for(User user : users){
            System.out.println(user.toString());
        }
    }
    public static void modifyTeacher(int id,String name, String username, String password,String qualification, String salary, String mobileNo, String address){
        for(Teacher teacher : teachers){
            if(teacher.getID() == id){
                teacher.setTeacherName(name);
                teacher.setUsername(username);
                if (!password.equals("")) {
                    teacher.setPassword(password);
                }
                teacher.setQualification(qualification);
                teacher.setSalary(Integer.parseInt(salary));
                teacher.setMobileNo(Integer.parseInt(mobileNo));
                teacher.setAddress(address);
                System.out.println("Modified teacher");
            }else{
                System.out.println("teacher not found");
            }
        }
    }
    public static ArrayList<User> getUsers(){
        return users;
    }
    public static ArrayList<Teacher> getTeachers(){
        return teachers;
    }
    public static void saveTofile(){
        try {
            File file = new File("UsersDB.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (User user : users) {
                bw.write(user.toFormattedString());
                bw.newLine();
            }
            bw.write("@{#Max_id="+User.getCount()+"}@end");
            bw.newLine();
            bw.close();
            fw.close();
            if (Test.isDebug()){
                System.out.println("File Written Successfully");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void readFromfile(){
        try {
            File file = new File("UsersDB.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int i = 0;
            while((line = br.readLine()) != null) {
                if (line.indexOf("User{@ID=") != -1) {
                    int idstart = line.indexOf("User{@ID=") + 9;
                    int idend = line.indexOf(", @Email=");
                    String test = line.substring(idstart, idend);
                    int id = Integer.parseInt(line.substring(idstart, idend));
                    int emailStart = idend + 9;
                    int emailEnd = line.indexOf(", @Password=");
                    String email = line.substring(emailStart, emailEnd);
                    int passwordStart = emailEnd + 12;
                    int passwrodEnd = line.indexOf("}@end");
                    String password = line.substring(passwordStart, passwrodEnd);
                    addUser(new User(id,email, password));
                    if (Test.isDebug()){
                        System.out.println("Read from DB" + (i+1));
                    }
                } else if (line.indexOf("Teacher{@Teacher_ID=") != -1) {
                    int idstart = line.indexOf("@Teacher_ID=") + 12;
                    int idend = line.indexOf(", @TeacherName=");
                    String id = line.substring(idstart, idend);
                    int nameStart = idend + 15;
                    int nameEnd = line.indexOf(", @Email=");
                    String name = line.substring(nameStart, nameEnd);
                    int emailStart = nameEnd + 9;
                    int emailEnd = line.indexOf(", @Password=");
                    String email = line.substring(emailStart, emailEnd);
                    int passwordStart = emailEnd + 12;
                    int passwrodEnd = line.indexOf(", @Qualification=");
                    String password = line.substring(passwordStart, passwrodEnd);
                    int qualificationStart = passwrodEnd + 17;
                    int qualificationEnd = line.indexOf(", @Salary=");
                    String qualification = line.substring(qualificationStart, qualificationEnd);
                    int salaryStart = qualificationEnd + 10;
                    int salaryEnd = line.indexOf(", @MobileNo=");
                    String salary = line.substring(salaryStart, salaryEnd);
                    int mobileNoStart = salaryEnd + 12;
                    int mobileNoEnd = line.indexOf(", @Address=");
                    String mobileNo = line.substring(mobileNoStart, mobileNoEnd);
                    int addressStart = mobileNoEnd + 11;
                    int addressEnd = line.indexOf("}@end");
                    String address = line.substring(addressStart, addressEnd);
                    addUser(new Teacher(Integer.parseInt(id),email,password,name,qualification,Integer.parseInt(salary),Integer.parseInt(mobileNo),address));
                    if (Test.isDebug()){
                        System.out.println("Read from DB" + (i+1) + " a Teacher");
                    }
                } else if (line.indexOf("@{#Max_id=") != -1) {
                    int idstart = line.indexOf("@{#Max_id=") + 10;
                    int idend = line.indexOf("}@end");
                    int count = Integer.parseInt(line.substring(idstart, idend));
                    User.setCount(count);
                }
                i++;
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

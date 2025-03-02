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
                System.out.println("found user = " + user.getUsername());
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
            FileWriter fw = new FileWriter("UsersDB.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (User user : users) {
                bw.write(user.toFormattedString());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("File Written Successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void readFromfile(){
        try {
            FileReader fr = new FileReader("UsersDB.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int i = 0;
            while((line = br.readLine()) != null) {
                if (line.indexOf("User{@ID=") != -1) {
                    int idstart = line.indexOf("@Id=") + 4;
                    int idend = line.indexOf(", @Email=");
                    String id = line.substring(idstart, idend);
                    int emailStart = idend + 9;
                    int emailEnd = line.indexOf(", @Password=");
                    String email = line.substring(emailStart, emailEnd);
                    int passwordStart = emailEnd + 12;
                    int passwrodEnd = line.indexOf("}");
                    String password = line.substring(passwordStart, passwrodEnd);
                    addUser(new User(email, password,true));
                    System.out.println("Read from DB" + (i+1));
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

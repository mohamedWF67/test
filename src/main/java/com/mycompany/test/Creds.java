/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test;

import java.util.ArrayList;

/**
 *
 * @author mohamed waleed
 */
public class Creds {
    private static ArrayList<User> users;
    private static ArrayList<Teacher> teachers;
    public Creds() {
        users = new ArrayList<>();
        teachers = new ArrayList<>();
    }
    public static String addUser(User user){
        users.add(user);
        return "User added";
    }

    public static String addUser(Teacher teacher){
        users.add(teacher);
        teachers.add(teacher);
        return "Teacher added";
    }
    public static String removeUser(User user){
        users.remove(user);
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
    public static ArrayList<User> getUsers(){
        return users;
    }
    public static ArrayList<Teacher> getTeachers(){
        return teachers;
    }
}

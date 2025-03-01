/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test;

/**
 *
 * @author mohamed waleed
 */
public class User {
    private static int count = 0;
    private int ID;
    private String username;
    private String password;

    public User() {
        ID = ++count;
        username = "";
        password = "";
    }
    public User(String username, String password) {
        ID = ++count;
        this.username = username;
        try {
            this.password = Encryption.Encrypt(password);
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public User(String username, String password,int type) {
        ID = ++count;
        this.username = username;
        try {
            this.password = Encryption.Encrypt(password,type);
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        setPassword(password,1);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPassword(String password, int type) {
        try {
            this.password = Encryption.Encrypt(password,type);
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + ID + '\''+
                ",username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

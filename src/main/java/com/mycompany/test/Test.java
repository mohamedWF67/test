/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.test;

import javax.swing.*;

/**
 *
 * @author mohamed waleed
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Creds creds = new Creds();
        Creds.addUser(new User("Waleed", "123456",1));
        Creds.addUser(new User("mohamed", "06072005",2));
        Creds.addUser(new User("mdxx421@gmail.com", "462305",3));
        Auth auth = new Auth();
        auth.setVisible(true);
    }
}

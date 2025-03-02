/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.test;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import javax.swing.*;

/**
 *
 * @author mohamed waleed
 */
public class Test {
    public static void main(String[] args) {
        FlatOneDarkIJTheme.setup();
        System.out.println("Hello World!");
        Creds creds = new Creds();
        Creds.readFromfile();
        Creds.addUser(new Teacher("mdxx","123456","walaa","PHD",5600,010,"15 wall street"));
        Auth auth = new Auth();
        auth.setVisible(true);
    }
}

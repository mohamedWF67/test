/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.test;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 *
 * @author mohamed waleed
 */
public class Test {
    private static boolean Debug = false;

    public static boolean isDebug() {
        return Debug;
    }

    public static void setDebug(boolean debug) {
        Test.Debug = debug;
    }

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            cleanup();
        }));
        FlatOneDarkIJTheme.setup();
        System.out.println("Hello World!");
        Creds creds = new Creds();
        Creds.readFromfile();
        Auth auth = new Auth();
        auth.setVisible(true);
    }

    private static void cleanup() {
        Creds.saveTofile();
        if (false){
            File file = new File("UsersDB.txt");
            if (file.exists()) {
                System.out.println("file exists");
                try {
                    Desktop.getDesktop().open(file);
                }catch (Exception e){
                    System.out.println("File Failed to open");
                    System.err.println(e);
                }
            }else {
                System.out.println("file does not exist");
            }
        }
    }
}

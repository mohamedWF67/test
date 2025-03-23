/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.test;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 *
 * @author mohamed waleed
 */
public class Test {
    private static boolean Debug = true;
    private static boolean fileSystemDebug = false;

    public static boolean isDebug() {
        return Debug;
    }

    public static void setDebug(boolean debug) {
        Test.Debug = debug;
    }

    public static boolean isFileSystemDebug() {
        return fileSystemDebug;
    }

    public static void setFileSystemDebug(boolean fileSystemDebug) {
        Test.fileSystemDebug = fileSystemDebug;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                cleanup();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        FlatOneDarkIJTheme.setup();
        new Creds();
        ArrayList<Object> values = new ArrayList<>();
        values.add(247019);
        values.add("Alice");
        values.add("123456");
        Teacher teacher = new Teacher();
        File_system.assignValues(teacher,values);
        Desktop.getDesktop().open(new File("test.txt"));
        Creds.setUsers(File_system.readAllFromFile("test.txt"));
        System.out.println(teacher.toString());
        Auth auth = new Auth();
        auth.setVisible(true);
    }

    private static void cleanup() throws IOException {
        //Creds.saveTofile();
        String filename = "test.txt";
        File_system.writeAllToFile(filename, new ArrayList<>(Creds.getUsers()));
        if (fileSystemDebug){
            File file = new File(filename);
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

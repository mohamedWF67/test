/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.test;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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

    public static File getFileFromResources(String fileName) throws IOException {
        ClassLoader classLoader = Test.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources: " + fileName);
        }

        // Create a temporary file and copy the content
        File tempFile = File.createTempFile("resource_", "_" + fileName);
        tempFile.deleteOnExit();
        Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return tempFile;
    }

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                cleanup();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        FlatOneDarkIJTheme.setup();
        Creds creds = new Creds();
        ArrayList<Object> values = new ArrayList<>();
        values.add(247019);
        values.add("Alice");
        values.add("123456");
        User user = new User();
        File_system.assignValues(user,values);
        Desktop.getDesktop().open(new File("test.txt"));
        Creds.readFromfile();
        File_system.writeToFile("test.txt", Creds.getTeachers().get(0));
        System.out.println(user.toString());
        Auth auth = new Auth();
        auth.setVisible(true);
    }

    private static void cleanup() throws IOException {
        Creds.saveTofile();
        if (fileSystemDebug){
            File file = getFileFromResources("UsersDB.txt");
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

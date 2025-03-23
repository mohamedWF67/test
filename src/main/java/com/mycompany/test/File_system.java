package com.mycompany.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class File_system {
    public static void writeToFile(String filename, Object obj) throws IOException {
        File file = new File(filename);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write(obj.getClass().getName() +"{");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Field[] fields = obj.getClass().getSuperclass().getDeclaredFields();
        if (fields.length > 0) {
            for (Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    bw.write("@#"+field.getName().toUpperCase()+"=" + field.get(obj)+",");
                } catch (IOException | IllegalAccessException  e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Printing " + obj.getClass().getSuperclass().getName());
        }else{
            System.out.println("Failed to print " + obj.getClass().getSuperclass().getName());
        }
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                bw.write("@#"+field.getName().toUpperCase()+"=" + field.get(obj)+",");
            } catch (IOException | IllegalAccessException  e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Printing " + obj.getClass().getName());
        bw.write("}@#END");
        bw.close();
        fw.close();
    }

    public static void assignValues(Object obj, ArrayList<Object> values) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int j = 0;
        for (int i = 0; i < fields.length && j < values.size(); i++) {
            fields[i].setAccessible(true);// Allow access to private fields
            System.out.println(fields[i].getName());
            try {
                boolean isStatic = Modifier.isStatic(fields[i].getModifiers());
                if(!isStatic){
                    fields[i].set(obj, values.get(j));// Assign value to the field
                    System.out.println("value of "+fields[i].getName()+" is "+fields[i].get(obj).toString());
                    j++;
                }
            } catch (IllegalAccessException | IllegalArgumentException e) {
                System.out.println("Error assigning value to " + fields[i].getName());
            }
        }
    }
}

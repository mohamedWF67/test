package com.mycompany.test;

import java.io.*;
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
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
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
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
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

    public static void readFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        line = br.readLine();
        String className = line.substring(0, line.indexOf("{"));
        Class<?> c = Class.forName(className);
        Field[] fields = c.getDeclaredFields();
        System.out.println("Class Name is " + c.getName());
        ArrayList<Object> values;
        int i = 0;
        for (Field field : c.getSuperclass().getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                i++;
                continue;
            }
            System.out.println(field.getName().toUpperCase());
            System.out.println("@#"+field.getName().toUpperCase()+"="+"|||"+",@#"+c.getSuperclass().getDeclaredFields()[i+1].getName().toUpperCase()+"="+"|||"+ (field.getName().length()+3));
            String fieldValue = line.substring((line.indexOf("@#"+field.getName().toUpperCase()+"=")+field.getName().length()+3) , line.indexOf(",@#"+c.getSuperclass().getDeclaredFields()[i+1].getName().toUpperCase()+"="));
            System.out.println(fieldValue);
            i++;
        }
        i=0;
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

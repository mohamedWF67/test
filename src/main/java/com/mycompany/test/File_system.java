package com.mycompany.test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

public class File_system {
    public static void writeAllToFile(String filename, ArrayList<Object> objects) throws IOException {
        File file = new File(filename);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Object obj : objects) {
            ArrayList<Field> fields = new ArrayList<>(Arrays.asList(obj.getClass().getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
            try {
                bw.write(obj.getClass().getName() + "{");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (fields.size() > 0) {
                for (Field field : fields) {
                    try {
                        if (Modifier.isStatic(field.getModifiers())) {continue;}
                        field.setAccessible(true);
                        bw.write("@#" + field.getName().toUpperCase() + "=" + field.get(obj) + ",");
                    } catch (IOException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Printing " + obj.getClass().getName());
            } else {
                System.out.println("Failed to print " + obj.getClass().getName());
            }
            bw.write("}@#END");
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
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

    public static ArrayList<Object> readAllFromFile(String filename) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<Object> arrayofobjects = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String className = line.substring(0, line.indexOf("{"));
            Class<?> c = Class.forName(className);
            ArrayList<Field> fields = new ArrayList<>(Arrays.asList(c.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
            System.out.println("Class Name is " + c.getName());
            ArrayList<Object> values = new ArrayList<>();
            int i = 0;
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    i++;
                    continue;
                }
                int indexForStart = (line.indexOf("@#"+field.getName().toUpperCase()+"=")+field.getName().length()+3);
                int indexForEnd=(i < fields.size()-1?line.indexOf(",@#"+fields.get(i+1).getName().toUpperCase()+"="):(line.indexOf(",}@#END")));
                System.out.println(field.getName().toUpperCase());
                if (i<fields.size()-1){
                    System.out.println("@#"+field.getName().toUpperCase()+"="+"|||"+",@#"+fields.get(i+1).getName().toUpperCase()+"="+"|||"+ (field.getName().length()+3));
                }else{
                    System.out.println("@#"+field.getName().toUpperCase()+"="+"|||"+",}@#END"+"|||"+ (field.getName().length()+3));
                }
                String fieldValue = line.substring(indexForStart , indexForEnd);
                values.add(convertToFieldType(field.getType(), fieldValue));
                System.out.println(fieldValue);
                i++;
            }
            /*var constructor = c.getClass().getConstructor();
            constructor.setAccessible(true); // Bypass private constructor*/
            Object obj = c.newInstance();
            assignValues(obj, values);
            System.out.println(obj.getClass().getSuperclass().getName()+": ");
            System.out.println(obj.toString());
            arrayofobjects.add(obj);
        }
        return arrayofobjects;
    }

    public static void readFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        line = br.readLine();
        String className = line.substring(0, line.indexOf("{"));
        Class<?> c = Class.forName(className);
        ArrayList<Field> fields = new ArrayList<>(Arrays.asList(c.getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(c.getDeclaredFields()));
        System.out.println("Class Name is " + c.getName());
        ArrayList<Object> values = new ArrayList<>();
        int i = 0;
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                i++;
                continue;
            }
            int indexForStart = (line.indexOf("@#"+field.getName().toUpperCase()+"=")+field.getName().length()+3);
            int indexForEnd=(i < fields.size()-1?line.indexOf(",@#"+fields.get(i+1).getName().toUpperCase()+"="):(line.indexOf(",}@#END")));
            System.out.println(field.getName().toUpperCase());
            if (i<fields.size()-1){
                System.out.println("@#"+field.getName().toUpperCase()+"="+"|||"+",@#"+fields.get(i+1).getName().toUpperCase()+"="+"|||"+ (field.getName().length()+3));
            }else{
                System.out.println("@#"+field.getName().toUpperCase()+"="+"|||"+",}@#END"+"|||"+ (field.getName().length()+3));
            }
            String fieldValue = line.substring(indexForStart , indexForEnd);
            values.add(convertToFieldType(field.getType(), fieldValue));
            System.out.println(fieldValue);
            i++;
        }
        Teacher teacher = new Teacher();
        assignValues(teacher,values);
        System.out.println("Teacher: ");
        System.out.println(teacher);
    }

    private static Object convertToFieldType(Class<?> fieldType, String value) {
        if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(value);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(value);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (fieldType == String.class) {
            return value;
        }
        throw new IllegalArgumentException("Unsupported field type: " + fieldType);
    }

    public static void assignValues(Object obj, ArrayList<Object> values) {
        Class<?> clazz = obj.getClass();
        ArrayList<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        int j = 0;
        for (int i = 0; i < fields.size() && j < values.size(); i++) {
            fields.get(i).setAccessible(true);// Allow access to private fields
            System.out.println(fields.get(i).getName());
            try {
                boolean isStatic = Modifier.isStatic(fields.get(i).getModifiers());
                if(!isStatic){
                    fields.get(i).set(obj, values.get(j));// Assign value to the field
                    System.out.println("value of "+fields.get(i).getName()+" is "+fields.get(i).get(obj).toString());
                    j++;
                }
            } catch (IllegalAccessException | IllegalArgumentException e) {
                System.out.println("Error assigning value to " + fields.get(i).getName());
            }
        }
    }
}

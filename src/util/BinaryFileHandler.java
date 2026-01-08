/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author ThanhDuy
 */
public class BinaryFileHandler {

    public static <T> boolean load(List<T> list, String fileName) {
        list.clear();
        File f = new File(fileName);
        if (!f.exists()) {
            return false;
        }
        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            List<T> data = (List<T>) ois.readObject();
            list.addAll(data);
            return true;
        } catch (Exception e) {
            System.out.println("Error reading binary file " + fileName + ": " + e.getMessage());
            return false;
        }
    }

    public static <T> boolean save(List<T> list, String fileName) {
        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list); // Ghi nguyên list xuống, Java tự lo phần còn lại
            return true;
        } catch (IOException e) {
            System.out.println("Error writing binary file " + fileName + ": " + e.getMessage());
            return false;
        }
    }
}

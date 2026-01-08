package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public abstract class TextFileHandler<T> {

    public boolean load(List<T> list, String fileName) {
        list.clear();
        File f = new File(fileName);
        if (!f.exists()) {
            System.out.println("File not found: " + fileName);
            return false;
        }

        try ( BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    T obj = parseLine(line);
                    if (obj != null) {
                        list.add(obj);
                    }
                }
            }
            return true;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading text file " + fileName + ": " + e.getMessage());
            return false;
        }
    }

    public boolean save(List<T> list, String fileName) {
        try ( PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (T item : list) {
                pw.println(item.toString());
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error writing text file " + fileName + ": " + e.getMessage());
            return false;
        }
    }

    public abstract T parseLine(String line);
}

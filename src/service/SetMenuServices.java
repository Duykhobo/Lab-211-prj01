/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.SetMenu;
import java.util.ArrayList;
import utilities.TextFileHandler;

/**
 *
 * @author ThanhDuy
 */
public class SetMenuServices extends ArrayList<SetMenu> {

    public SetMenuServices() {
        this.loadData();
    }

    private void loadData() {
        new TextFileHandler<SetMenu>() {
            @Override
            public SetMenu parseLine(String line) {
                return parseMenuFromLine(line);
            }
        }.load(this, "FeastMenu.csv");
    }

    private SetMenu parseMenuFromLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(",");

        if (parts.length < 4) {
            System.err.println("Lỗi [Thiếu dữ liệu]: Dòng này không đủ 4 cột -> " + line);
            return null;
        }

        String code = parts[0].trim();
        String name = parts[1].trim();
        String priceStr = parts[2].trim();
        String ingredients = parts[3].trim();

        if (code.isEmpty() || name.isEmpty()) {
            System.err.println("Lỗi [Dữ liệu rỗng]: Code hoặc Tên món bị trống -> " + line);
            return null;
        }

        double price = 0;
        try {
            price = Double.parseDouble(priceStr);

            if (price < 0) {
                System.err.println("Lỗi [Logic]: Giá tiền không được âm -> " + line);
                return null;
            }

        } catch (NumberFormatException e) {
            System.err.println("Lỗi [Định dạng số]: Giá tiền '" + priceStr + "' không hợp lệ -> " + line);
            return null;
        }

        return new SetMenu(code, name, price, ingredients);
    }

    public void displayAll() {
        System.out.println("List of Set Menus for ordering party:");
        System.out.println("--------------------------------------------------");
    }
}

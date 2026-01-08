/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author ThanhDuy
 */
public class ErrorHandler {

    public static void handle(Action action) {
        try {
            action.execute();

        } catch (IllegalArgumentException e) {
            System.err.println(">> [VALIDATION ERROR]: " + e.getMessage());

        } catch (NullPointerException e) {
            System.err.println(">> [SYSTEM BUG]: Dữ liệu bị rỗng đột ngột!");
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println(">> [SYSTEM ERROR]: " + e.getMessage());
        }
    }
}

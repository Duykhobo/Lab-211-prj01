/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package util;

/**
 *
 * @author ThanhDuy
 */
public interface Acceptable {

    public final String CUS_ID_VALID = "^[CcGgKk]\\d{4}$";
    public final String NAME_VALID = "^.{2,25}$";
    public final String PHONE_VALID = "^0\\d{9}$";
    public final String EMAIL_VALID = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public final String INTEGER_VALID = "^-?\\d+$";
    public final String POSITIVE_INT_VALID = "^[1-9]\\d*$";
    public final String DOUBLE_VALID = "^-?\\d+(\\.\\d+)?$";
    public final String POSITIVE_DOUBLE_VALID = "^([1-9]\\d*)(\\.\\d+)?$";
    public final String TIME_VALID = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}

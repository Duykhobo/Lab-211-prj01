/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ThanhDuy
 */
public class Customer implements Serializable {

    private String code;
    private String name;
    private String phoneNumber;
    private String email;

    public Customer() {
    }

    public Customer(String code, String name, String phoneNumber, String email) {
        this.code = code;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void updateDetails(String newName, String newPhone, String newEmail) {
        this.name = newName;
        this.phoneNumber = newPhone;
        this.email = newEmail;
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-25s | %-12s | %-35s",
                this.code, this.name, this.phoneNumber, this.email);
    }

}

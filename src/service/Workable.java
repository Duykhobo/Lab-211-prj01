/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import model.Customer;

/**
 *
 * @author ThanhDuy
 */
public interface Workable<T> {

    void addNew(T x);

    void update(T x);

    T searchByCode(String code);

    void showAll();

    void loadFromFile(String fileName);

    void saveToFile(String fileName);
}

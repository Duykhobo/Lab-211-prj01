package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private String code;
    private String customerCode;
    private String setMenuCode;
    private int numberOfTables;
    private Date eventDate;
    private double totalCost;

    public Order(String customerCode, String setMenuCode, int numberOfTables, Date eventDate, double menuPrice) {
        this.code = generateOrderCode();
        this.customerCode = customerCode;
        this.setMenuCode = setMenuCode;
        this.numberOfTables = numberOfTables;
        this.eventDate = eventDate;
        this.totalCost = menuPrice * numberOfTables;
    }

    private String generateOrderCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    public String getCode() {
        return code;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getSetMenuCode() {
        return setMenuCode;
    }

    public void setSetMenuCode(String setMenuCode) {
        this.setMenuCode = setMenuCode;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(customerCode, order.customerCode)
                && Objects.equals(setMenuCode, order.setMenuCode)
                && Objects.equals(eventDate, order.eventDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerCode, setMenuCode, eventDate);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = sdf.format(eventDate);

        return String.format("%-10s | %-12s | %-8s | %-8s | %-4d | %,12.0f",
                code, dateStr, customerCode, setMenuCode, numberOfTables, totalCost);
    }
}

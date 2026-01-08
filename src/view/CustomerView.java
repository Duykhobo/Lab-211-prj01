package view;

import model.Customer;
import util.Acceptable;
import util.Inputter;
import java.util.List;
import java.util.function.Predicate;

public class CustomerView {

    public String inputIdForRegister(Predicate<String> isDuplicate) {
        return Inputter.wrapRetry(() -> {
            String c = Inputter.getAString(
                    "Enter Customer Code (C/G/K + 4 digits): ",
                    "Invalid format! (Example: C0001, G1234)",
                    Acceptable.CUS_ID_VALID
            );
            if (isDuplicate.test(c)) {
                throw new Exception("Customer ID '" + c + "' already exists.");
            }
            return c;
        });
    }

    public Customer inputCustomerDetails(String code) {
        String name = Inputter.getAString(
                "Enter Name: ",
                "Invalid Name! (Name must be between 2-25 characters)",
                Acceptable.NAME_VALID
        );
        String phone = Inputter.getAString(
                "Enter Phone: ",
                "Invalid Phone! (Must start with 0 and contain exactly 10 digits)",
                Acceptable.PHONE_VALID
        );
        String email = Inputter.getAString(
                "Enter Email: ",
                "Invalid Email! (Correct format example: user@domain.com)",
                Acceptable.EMAIL_VALID
        );
        return new Customer(code, name, phone, email);
    }

    public String inputNameForSearch() {
        return Inputter.getAString("Enter name to search: ", "", Acceptable.NAME_VALID);
    }

    public String inputIdForUpdate(Predicate<String> isExist) {
        return Inputter.wrapRetry(() -> {
            String c = Inputter.getAString("Enter Customer Code to update: ",
                    "Invalid format!", Acceptable.CUS_ID_VALID);
            // Check tồn tại
            if (!isExist.test(c)) {
                throw new Exception("Customer ID '" + c + "' does not exist.");
            }
            return c;
        });
    }

    // 4. Các hàm hiển thị
    public void displayMessage(String msg) {
        System.out.println(">> " + msg);
    }

    public void displayError(String err) {
        System.err.println("Error: " + err);
    }

    public void displayCustomer(Customer c) {
        System.out.println(">> Found: " + c.toString());
    }

    public void displayList(List<Customer> list) {
        if (list.isEmpty()) {
            System.out.println(">> List is empty.");
        } else {
            for (Customer c : list) {
                System.out.println(c);
            }
        }
    }
}

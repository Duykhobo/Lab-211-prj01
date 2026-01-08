package controller;

import java.util.List;
import model.Customer;
import service.CustomerServices;
import view.CustomerView;

/**
 * @author ThanhDuy
 */
public class CustomerController {

    private CustomerServices customerService;
    private CustomerView view;
    private final String FILE_NAME = "customers.dat";

    public CustomerController() {
        this.customerService = new CustomerServices();
        this.customerService.loadFromFile(FILE_NAME);
        this.view = new CustomerView();
    }

    public void register() {
        view.displayMessage("----- REGISTER NEW CUSTOMER -----");
        try {
            // 1. Nhập ID (Check trùng)
            String code = view.inputIdForRegister(id -> customerService.searchByCode(id) != null);

            // 2. Nhập chi tiết (Truyền thêm logic check trùng Phone và Email vào View)
            Customer newCus = view.inputCustomerDetails(
                    code,
                    phone -> customerService.isPhoneExist(phone),
                    email -> customerService.isEmailExist(email));

            // 3. Lưu (Lúc này service.addNew không lo bị lỗi trùng nữa vì view đã chặn rồi)
            customerService.addNew(newCus);
            customerService.saveToFile(FILE_NAME);
            view.displayMessage("Register and save successfully!");

        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    public void display() {

        view.displayList(customerService);
    }

    public void save() {
        customerService.saveToFile(FILE_NAME);
    }

    public void update() {
        view.displayMessage("----- UPDATE CUSTOMER INFO -----");

        try {
            // 1. Điều phối View nhập ID cần sửa (Check phải tồn tại)
            String code = view.inputIdForUpdate(id -> customerService.searchByCode(id) != null);

            // 2. Lấy data cũ để hiện ra (nếu muốn)
            Customer oldCus = customerService.searchByCode(code);
            view.displayCustomer(oldCus);
            view.displayMessage("Enter new info:");

            // 3. Điều phối View nhập thông tin mới
            Customer tempInfo = view.inputCustomerDetails(
                    code,
                    phone -> customerService.isPhoneExist(phone),
                    email -> customerService.isEmailExist(email));

            // 4. Gọi Service/Model update logic
            oldCus.updateDetails(tempInfo.getName(), tempInfo.getPhoneNumber(), tempInfo.getEmail());

            // 5. Lưu file
            customerService.saveToFile(FILE_NAME);
            view.displayMessage("Update success!");

        } catch (Exception e) {
            view.displayError("Update failed: " + e.getMessage());
        }
    }

    public void search() {
        view.displayMessage("----- SEARCH CUSTOMER BY NAME -----");
        try {
            String nameToFind = view.inputNameForSearch();

            List<Customer> result = customerService.searchByName(nameToFind);
            if (result.isEmpty()) {
                view.displayMessage("No customer found with name: " + nameToFind);
            } else {
                view.displayMessage("Found " + result.size() + " customer(s):");
                view.displayList(result);
            }
        } catch (Exception e) {
            view.displayError("Search error: " + e.getMessage());
        }
    }
}

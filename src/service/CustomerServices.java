package service;

import java.util.ArrayList;
import java.util.List;
import model.Customer;
import utilities.Acceptable;
import utilities.BinaryFileHandler;
import utilities.Validation;

/**
 * @author ThanhDuy
 */
public class CustomerServices extends ArrayList<Customer> implements Workable<Customer> {

    @Override
    public void addNew(Customer x) {
        // 1. Validation cơ bản (Null/Empty)
        Validation.checkNullObject(x, "Customer object cannot be null.");
        Validation.checkNullOrEmpty(x.getCode(), "Customer ID cannot be empty.");

        // 2. Validation Định dạng (Regex) bằng Acceptable
        if (!Acceptable.isValid(x.getCode(), Acceptable.CUS_ID_VALID)) {
            throw new IllegalArgumentException("Customer ID is invalid. Must start with C, G, K followed by 4 digits.");
        }

        // Check Name (2-25 ký tự)
        if (!Acceptable.isValid(x.getName(), Acceptable.NAME_VALID)) {
            throw new IllegalArgumentException("Customer Name is invalid. Length must be between 2 and 25.");
        }

        // Check Phone (10 số, bắt đầu bằng 0)
        if (!Acceptable.isValid(x.getPhoneNumber(), Acceptable.PHONE_VALID)) {
            throw new IllegalArgumentException("Phone number must be 10 digits and start with 0.");
        }

        // Check Email
        if (!Acceptable.isValid(x.getEmail(), Acceptable.EMAIL_VALID)) {
            throw new IllegalArgumentException("Email format is invalid.");
        }

        // 3. Check trùng ID (Nghiệp vụ)
        Validation.checkDuplicate(this.searchByCode(x.getCode()),
                "Customer ID " + x.getCode() + " already exists.");

        // 4. Thêm vào list
        this.add(x);
    }

    @Override
    public void update(Customer newInfo) {
        // 1. Validation đầu vào
        Validation.checkNullObject(newInfo, "Update info cannot be null.");
        Validation.checkNullOrEmpty(newInfo.getCode(), "Customer ID to update cannot be empty.");

        // 2. Tìm khách hàng cũ
        Customer oldCus = this.searchByCode(newInfo.getCode());

        

        // 3. Check tồn tại (Nghiệp vụ)
        // Nếu không tìm thấy (kết quả == null) -> Báo lỗi ngay
        Validation.checkExists(
                oldCus,
                "Customer ID " + newInfo.getCode() + " not found to update.");

        // 4. Cập nhật dữ liệu
        oldCus.updateDetails(newInfo.getName(), newInfo.getPhoneNumber(), newInfo.getEmail());
    }

    @Override
    public Customer searchByCode(String code) {
        // Trim code để tránh lỗi do thừa khoảng trắng
        if (code == null) {
            return null;
        }

        for (Customer cus : this) {
            if (cus.getCode().equalsIgnoreCase(code.trim())) {
                return cus;
            }
        }
        return null;
    }

    public ArrayList<Customer> searchByName(String name) {
        ArrayList<Customer> result = new ArrayList<>();
        // Nếu từ khóa rỗng thì trả về list rỗng hoặc toàn bộ list tùy nghiệp vụ
        if (name == null || name.trim().isEmpty()) {
            return result;
        }

        for (Customer cus : this) {
            // Tìm kiếm tương đối (contains) và không phân biệt hoa thường
            if (cus.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(cus);
            }
        }
        return result;
    }

    @Override
    public void showAll() {
        if (this.isEmpty()) {
            System.out.println(">> The list is empty.");
            return;
        }
        // In tiêu đề cột cho đẹp
        System.out.printf("%-6s | %-25s | %-12s | %-35s\n", "CODE", "NAME", "PHONE", "EMAIL");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Customer cus : this) {
            System.out.println(cus);
        }
    }

    // ================== CÁC HÀM XỬ LÝ FILE==================
    @Override
    public void loadFromFile(String fileName) {
        boolean result = BinaryFileHandler.load(this, fileName);
        if (result) {
            System.out.println(">> [System] Loaded customer data from " + fileName);
        } else {
            System.out.println(">> [System] No customer data file found. Starting with empty list.");
        }
    }

    @Override
    public void saveToFile(String fileName) {
        boolean result = BinaryFileHandler.save(this, fileName);
        if (result) {
            System.out.println(">> Saved successfully to " + fileName);
        } else {
            System.err.println(">> [System] Error saving customer data.");
        }
    }

}

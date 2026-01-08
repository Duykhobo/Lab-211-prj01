package view;

import utilities.Menu;

/**
 * Class này CHỈ chịu trách nhiệm hiển thị Menu và trả về lựa chọn
 */
public class MainView {

    private Menu<String> mainMenu;
    private Menu<String> subMenu;

    public MainView() {
        // Cấu hình Menu chính
        mainMenu = new Menu<>("TRADITIONAL FEAST ORDER MANAGEMENT");
        mainMenu.addNewOption("Register customers"); // 1
        mainMenu.addNewOption("Update customer information"); // 2
        mainMenu.addNewOption("Search for customer information by name");// 3
        mainMenu.addNewOption("Display feast menus"); // 4
        mainMenu.addNewOption("Place a feast order"); // 5
        mainMenu.addNewOption("Update order information"); // 6
        mainMenu.addNewOption("Save data to file"); // 7
        mainMenu.addNewOption("Display Customer or Order lists"); // 8
        mainMenu.addNewOption("Quit"); // 9

        // Cấu hình Sub Menu (cho chức năng số 8)
        subMenu = new Menu<>("DISPLAY LISTS");
        subMenu.addNewOption("Display Customer List");
        subMenu.addNewOption("Display Order List");
        subMenu.addNewOption("Return to Main Menu");
    }

    // Hàm hiển thị và lấy lựa chọn Menu chính
    public int getMainMenuChoice() {
        mainMenu.print();
        return mainMenu.getChoice();
    }

    // Hàm hiển thị và lấy lựa chọn Sub Menu
    public int getSubMenuChoice() {
        subMenu.print();
        return subMenu.getChoice();
    }
}

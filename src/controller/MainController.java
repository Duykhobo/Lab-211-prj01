package controller;

import view.MainView;
import utilities.ErrorHandler;

/**
 * Class này CHỨA LOGIC điều hướng (Switch-case)
 */
public class MainController {

    private MainView view;
    private CustomerController customerController;
    private MenuController menuController;
    private OrderController orderController;

    public MainController() {
        this.view = new MainView();
        this.customerController = new CustomerController();
        this.menuController = new MenuController();
        this.orderController = new OrderController();
    }

    public void run() {
        int choice;
        do {
            // 1. View chỉ việc lấy số về
            choice = view.getMainMenuChoice();

            // 2. Controller quyết định làm gì với số đó
            final int finalChoice = choice;
            ErrorHandler.handle(() -> processMainChoice(finalChoice));

        } while (choice != 9);
    }

    // Tách switch-case ra một hàm riêng cho gọn
    private void processMainChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                customerController.register();
                break;
            case 2:
                customerController.update();
                break;
            case 3:
                customerController.search();
                break;
            case 4:
                // menuController.display();
                break;
            case 5:
                // orderController.placeOrder();
                break;
            case 6:
                // orderController.updateOrder();
                break;
            case 7:
                // orderController.save();
                System.out.println(">> Data saved successfully.");
                break;
            case 8:
                processSubMenu();
                break;
            case 9:
                System.out.println("Bye.");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private void processSubMenu() {
        int subChoice;
        do {
            subChoice = view.getSubMenuChoice();
            final int finalSubChoice = subChoice;

            ErrorHandler.handle(() -> processSubMenuOption(finalSubChoice));
        } while (subChoice != 3);
    }

    private void processSubMenuOption(int choice) throws Exception {
        switch (choice) {
            case 1:
                System.out.println("--- Customer List ---");
                customerController.display();
                break;
            case 2:
                System.out.println("--- Order List ---");
                // orderController.display();
                break;
            case 3:
                System.out.println("Returning...");
                break;
            default:
                throw new Exception("Invalid choice!"); // Ném lỗi để ErrorHandler bắt
        }
    }
}

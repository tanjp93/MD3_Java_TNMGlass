package projectBase_00.view;

import projectBase_00.config.Config;
import projectBase_00.config.InputMethod;
import projectBase_00.controller.UserController;
import projectBase_00.model.user.User;

public class
Navbar {
    public Navbar() {
        UserController userController = new UserController();
        User userLogin = userController.getUserLogin();
        System.out.println("******************* Menu *******************");
        System.out.println("1. Category Product ");
        System.out.println("2. Search Product ");
//        System.out.println("2. About Us");
//        System.out.println("3. Production Process");
//        System.out.println("4. Ability");
//        System.out.println("5. Recruitment");
//        System.out.println("6. Contact");
        if (userLogin == null) {
            System.out.println("7. SignIn/SignUp");
        } else {
            System.out.println("7. " + userLogin.getName());
        }
        System.out.println("Enter your choice : ");
//        int choiceMenu = Integer.parseInt(Config.scanner.nextLine());
        int choiceMenu = InputMethod.getInteger();
        switch (choiceMenu) {
            case 1:
                new CategoryView();
                break;
            case 2:
                new ProductView().searchProductByName();
                new ProductView().buyOrBack();
                break;
//            case 2:
//                new AboutUsView().showAllInfo();
//                new Navbar();
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
//            case 6:
//                break;
            case 7:
                new UserView().showLogInLogOut();
                new Navbar();
                break;
        }
    }

    public static void main(String[] args) {
        new Navbar();
    }
}

package projectBase_00.view;

import projectBase_00.config.Config;
import projectBase_00.config.InputMethod;
import projectBase_00.controller.UserController;
import projectBase_00.dto.request.LogInDTO;
import projectBase_00.dto.request.RegisterDTO;
import projectBase_00.dto.response.ResponseMessage;
import projectBase_00.model.role.Role;
import projectBase_00.model.role.RoleName;
import projectBase_00.model.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserView {
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();

    public void showLogInLogOut() {
        User userLogin = userController.getUserLogin();
        Set<Role> roleSetLogin = null;
        if (userLogin != null) {
            roleSetLogin = userLogin.getRoles();
            System.out.println("0. Back to menu");
            System.out.println("1. Show personalInfo");
            System.out.println("2. Edit personalInfo");
            System.out.println("3. LogOut");
            roleSetLogin.forEach(role -> {
                if (role.getRoleName() == RoleName.ADMIN) {
                    System.out.println("4. List User Management");
                    System.out.println("5. Product Management");
                }

            });
            System.out.println("6. List Order");


            int chooseMenu = InputMethod.getInteger();
            switch (chooseMenu) {
                case 0:
                    new Navbar();
                    break;
                case 1:
                    showLoginUser();
                    new Navbar();
                    break;
                case 2:
                    editUserByPersonal();
                    break;
                case 3:
                    userController.logOutUser();
                    break;
                case 4:
                    roleSetLogin.forEach(role -> {
                        if (role.getRoleName() == RoleName.ADMIN) {
                            showListUser();
                        } else {
                            System.out.println("Invalid Choice");
                            new Navbar();
                        }
                    });
                    editUserByAdmin();
                    new Navbar();
                    break;
                case 5:
                    roleSetLogin.forEach(role -> {
                        if (role.getRoleName() == RoleName.ADMIN||role.getRoleName() == RoleName.PM) {
                         new ProductView().menuProduct();
                        } else {
                            System.out.println("Invalid Choice");
                            new Navbar();
                        }
                    });
                    editUserByAdmin();
                    new Navbar();
                    break;
                case 6:
                    roleSetLogin.forEach(role -> {
                        if (role.getRoleName() == RoleName.ADMIN || role.getRoleName() == RoleName.PM) {
                            new CartView().showListCart();
                        } else {
                            new CartView().showUserCart(userLogin);
                            new CartView().menuBuyMoreOrDeleteProduct(userLogin);
                        }
                    });
                    break;
                default:
                    System.out.println("Invalid Choice");
                    new Navbar();
            }
        } else {
            System.out.println("1. Register");
            System.out.println("2. Login");
            int chooseMenu = InputMethod.getInteger();
            switch (chooseMenu) {
                case 1:
                    new UserView().formRegister();
                    formLogin();
                    new Navbar();
                    break;
                case 2:
                    new UserView().formLogin();
                    new Navbar();
                    break;
                default:
                    System.out.println("Invalid Input Number");
                    new Navbar();
            }
        }
    }

    public void formRegister() {
        int id = 0;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        System.out.println("Enter the name: ");
        String name = InputMethod.getString();
        System.out.println("Enter the username: ");
        String username = InputMethod.getString();
        System.out.println("Enter the email: ");
        String email = InputMethod.getString();
        email = userController.checkEmail(email);
        System.out.println("Enter the password: ");
        String password = InputMethod.getString();
        password = userController.checkPassword(password);
        Set<String> strRole = new HashSet<>();
        strRole.add("user");
        RegisterDTO register = new RegisterDTO(id, name, username, email, password, strRole);
        while (true) {
            ResponseMessage responseMessage = userController.register(register);
            if (responseMessage.getMessage().equals("user_existed")) {
                System.out.println("Username existed! Please input another username !");
                System.out.println("Enter the username: ");
                username = InputMethod.getString();
                register.setUsername(username);
            } else if (responseMessage.getMessage().equals("email_existed")) {
                System.out.println("Email existed! Please input another email !");
                System.out.println("Enter the Email: ");
                email = InputMethod.getString();
                register.setEmail(email);
            } else if (responseMessage.getMessage().equals("create_success")) {
                System.out.println("Register Successfully !");
                formLogin();
                break;
            }
        }
    }

    public void formLogin() {
        System.out.println("Form Login!");
        System.out.println("Enter your username: ");
        String username = InputMethod.getString();
        System.out.println("Enter your password: ");
        String password = InputMethod.getString();
        LogInDTO logInDTO = new LogInDTO(username, password);
        while (true) {
            ResponseMessage responseMessage = userController.Login(logInDTO);
            if (responseMessage.getMessage().equals("login_fail")) {
                System.out.println("Please check your account again !");
                System.out.println("Enter your username: ");
                username = InputMethod.getString();
                System.out.println("Enter your password: ");
                password = InputMethod.getString();
                logInDTO.setUsername(username);
                logInDTO.setPassword(password);
            } else if (responseMessage.getMessage().equals("login_success")) {
                System.out.println("******************* Welcome " + userController.getUserLogin().getName() + " *******************!");
                new Navbar();
                break;
            }
        }
    }

    public void showListUser() {
        System.out.println("*****************************User Manage*****************************");
        System.out.println("--ID----Username----Email----Role----Status----Password----");
        for (User user : userList) {
            System.out.println("--" + user.getId() + "----" + user.getUsername() + "----" + user.getEmail() + "----" +
                    user.getRoles().toString() + ((!user.isStatus()) ? "Block" : "Active") + "----" + user.getPassword());
        }
    }

    public User getUserLoginInfo() {
        User loginUser = userController.getUserLogin();
        return loginUser;
    }

    public void showLoginUser() {
        User loginUser = getUserLoginInfo();
        System.out.println("----Name----username----email---------password----avatar----roles");
        for (Role role : loginUser.getRoles()) {
            System.out.print("----" + loginUser.getName() + "----" + loginUser.getUsername() + "----" + loginUser.getEmail() + "----" + loginUser.getPassword() + "----" + ((loginUser.getAvatar() == null) ? " No Img " : "Update new One"));
            if (role.getRoleName() == RoleName.PM) {
                System.out.println("----" + "MANAGER" + "----");
            } else if (role.getRoleName() == RoleName.USER) {
                System.out.println("----" + "USER" + "----");
            } else if (role.getRoleName() == RoleName.ADMIN) {
                System.out.println("----" + "ADMIN" + "----");
            }
        }
    }

    public void editUserByPersonal() {
        showLoginUser();
        User loginUser = getUserLoginInfo();
        System.out.println("Enter the name: ");
        String name = InputMethod.getString();
        System.out.println("Enter the email: ");
        String email = userController.checkEmail(InputMethod.getString());
        System.out.println("Enter the password: ");
        String password = userController.checkPassword(InputMethod.getString());
        System.out.println("Set Avatar: ");
        String avatar = InputMethod.getString();
        User user = new User(loginUser.getId(), name, loginUser.getUsername(), email, password, loginUser.isStatus(), avatar, loginUser.getRoles());
        userController.updateUser(user);
        System.out.println("Update Info success ! ");
        userController.logOutUser();
    }

    public void editUserByAdmin() {
        System.out.println("Enter UserId to update");
        int id = InputMethod.getInteger();
        User user = userController.findUserById(id);
        System.out.println("Enter the role: ");
        System.out.println("1. ADMIN");
        System.out.println("2. PM");
        System.out.println("3. USER");
        int choice = InputMethod.getInteger();
        Set<Role> roleSet = new HashSet<>();
        Role role = new Role(0, (choice == 1) ? RoleName.ADMIN : (choice == 2) ? RoleName.PM : RoleName.USER);
        roleSet.add(role);
        System.out.println("Change Status");
        System.out.println("0. Block");
        System.out.println("1. Active");
        int setStt = InputMethod.getInteger();
        boolean status = (setStt == 0) ? false : true;
        user.setRoles(roleSet);
        user.setStatus(status);
        userController.updateUser(user);
        System.out.println("Update Info success ! ");
    }
}


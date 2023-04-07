package projectBase_00.view;

import projectBase_00.controller.UserController;
import projectBase_00.model.role.Role;
import projectBase_00.model.role.RoleName;
import projectBase_00.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProfileView {
    UserController userController = new UserController();

    public ProfileView(UserController userController) {
        User user = userController.getUserLogin();
        if (user != null) {
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            if (roles.get(0).getRoleName() == RoleName.ADMIN) {
                System.out.println("PHần dành cho admin");
            } else if (roles.get(0).getRoleName() == RoleName.PM) {
                System.out.println("Phần dành cho PM");
            }else if (roles.get(0).getRoleName() == RoleName.USER){
                System.out.println("Phần dành cho User");
            }
        }
    }
}
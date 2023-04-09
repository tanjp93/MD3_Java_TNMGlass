package projectBase_00.controller;

import projectBase_00.config.Config;
import projectBase_00.dto.request.LogInDTO;
import projectBase_00.dto.request.RegisterDTO;
import projectBase_00.dto.response.ResponseMessage;
import projectBase_00.model.role.Role;
import projectBase_00.model.role.RoleName;
import projectBase_00.model.user.User;
import projectBase_00.service.role.IRoleService;
import projectBase_00.service.role.RoleServiceIMPL;
import projectBase_00.service.user.IUserService;
import projectBase_00.service.user.UserServiceIMPL;
import projectBase_00.view.CategoryView;
import projectBase_00.view.Navbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {
    private IUserService userService = new UserServiceIMPL();
    private IRoleService roleService = new RoleServiceIMPL();
    Set<Role> roleSetLogin = new HashSet<>();

    public ResponseMessage register(RegisterDTO userDTO) {
        if (userService.existedByUsername(userDTO.getName())) {
            //duplicate username
            return new ResponseMessage("user_existed");
        }
        if (userService.existedByEmail(userDTO.getEmail())) {
            //duplicate email
            return new ResponseMessage("email_existed");
        }
        Set<String> strRole = userDTO.getStrRole();
        Set<Role> roleSet = new HashSet<>();
        strRole.forEach(role -> {
            switch (role) {
                case "admin":
                    roleSet.add(roleService.findByName(RoleName.ADMIN));
                    break;
                case "pm":
                    roleSet.add(roleService.findByName(RoleName.PM));
                    break;
                default:
                    roleSet.add(roleService.findByName(RoleName.USER));
                    break;
            }
        });
        User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), roleSet);
        userService.save(user);
        return new ResponseMessage("create_success");
    }

    public List<User> getListUser() {
        return userService.findAll();
    }

    public ResponseMessage Login(LogInDTO logInDTO) {
        if (userService.checkLogin(logInDTO.getUsername(), logInDTO.getPassword())) {
            //login success
            return new ResponseMessage("login_success");
        } else return new ResponseMessage("login_fail");
    }


    public User getUserLogin() {
        return userService.getCurrentUser();
    }

    public void updateUser(User user) {
        userService.save(user);
    }

    public String checkEmail(String email) {
        String pattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(email);
        if (matcher.matches()) {
            return email;
        } else {
            System.err.println("Invalid email address");
            System.out.println("Please try again ! ");
            email = Config.scanner.nextLine();
            checkEmail(email);
        }
        return email;
    }

    public boolean logOutUser() {
        if (userService.exit()) {
            new Navbar();
            return true;
        } else return false;
    }

    public String checkPassword(String password) {
        String pattern = "^[\\w]{6,}$";
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(password);
        if (matcher.matches()) {
            return password;
        } else {
            System.err.println("Invalid Password, Please try again !");
            password = Config.scanner.nextLine();
            checkPassword(password);
        }
        return password;
    }
    public User findUserById(int id){
      return userService.findById(id);
    }
    public List<ResponseMessage> checkRole() {
        List<ResponseMessage> listResponse=new ArrayList<>();
        User user = getUserLogin();
        if (user != null) {
            roleSetLogin = user.getRoles();
            roleSetLogin.forEach(role -> {
                if (role.getRoleName() == RoleName.ADMIN) {
                    listResponse.add((new ResponseMessage("ADMIN")));
                } else if (role.getRoleName() == RoleName.PM) {
                    listResponse.add((new ResponseMessage("PM")));
                }else {
                    listResponse.add(new ResponseMessage("user"));
                }
            });
        }
        return listResponse;
    }

}

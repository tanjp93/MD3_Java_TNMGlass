package projectBase_00.service.user;

import projectBase_00.config.Config;
import projectBase_00.dto.request.RegisterDTO;
import projectBase_00.model.user.User;
import projectBase_00.view.Navbar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class UserServiceIMPL implements IUserService {
    List<User> userList = new Config<User>().readFromFile(Config.PATH_USER);
    List<User> userLogin = new Config<User>().readFromFile(Config.PATH_LOGIN);
    @Override
    public List findAll() {
        return userList;
    }

    @Override
    public void save(User user) {
        //create User !
        if (findById(user.getId()) == null) {
            userList.add(user);
        } else {
            //update user
            userList.set(userList.indexOf(findById(user.getId())), user);
        }
        new Config<User>().writeToFile(Config.PATH_USER, userList);
    }

    @Override
    public User findById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (findById(id) != null) {
            System.out.println("User is not found !");
        } else {
            userList.remove(findById(id));
        }
        new Config<User>().writeToFile(Config.PATH_USER, userList);
    }


    @Override
    public boolean existedByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username) && userList.get(i).getPassword().equals(password)) {
                //login success
                userLogin.add(userList.get(i));
                new Config<User>().writeToFile(Config.PATH_LOGIN, userLogin);
                return  true;
            }
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        if(userLogin.size()!=0){
            return userLogin.get(0);
        }else  return null;
    }

    @Override
    public boolean exit() {
        if (getCurrentUser()==null){
            return false;
        }else{
            try {
                PrintWriter pw = new PrintWriter(Config.PATH_LOGIN);
                pw.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }
}

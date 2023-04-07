package projectBase_00.service.user;

import projectBase_00.model.user.User;
import projectBase_00.service.IGenericService;

public interface IUserService extends IGenericService <User>{
    boolean existedByUsername(String name);
    boolean existedByEmail(String email);
    boolean checkLogin(String username,String password);
    User getCurrentUser();
    boolean exit();
}

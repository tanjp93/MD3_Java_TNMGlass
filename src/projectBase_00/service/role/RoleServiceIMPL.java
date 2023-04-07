package projectBase_00.service.role;

import projectBase_00.model.role.Role;
import projectBase_00.model.role.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements IRoleService {
    public static List<Role> roleList = new ArrayList<>();

    static {
        roleList.add(new Role(1, RoleName.ADMIN));
        roleList.add(new Role(2, RoleName.PM));
        roleList.add(new Role(3, RoleName.USER));
    }

    @Override
    public List<Role> findAll() {
        return roleList;
    }

    @Override
    public Role findByName(RoleName name) {
        for (Role role:roleList ) {
            if (role.getRoleName().equals(name)){
                return role;
            }
        }
        return null;
    }
}

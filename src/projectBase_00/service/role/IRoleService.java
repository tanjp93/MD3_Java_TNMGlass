package projectBase_00.service.role;

import projectBase_00.model.role.Role;
import projectBase_00.model.role.RoleName;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
    Role findByName(RoleName name);
}

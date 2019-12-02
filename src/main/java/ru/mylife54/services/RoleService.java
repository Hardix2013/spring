package ru.mylife54.services;


import ru.mylife54.models.Role;

import java.util.Collection;

public interface RoleService  {
    Role getRole(String name);
    Collection<Role> getAll();
    boolean deleteRole(Role role);
    Role add(Role role);
    Role update(Role role);
}

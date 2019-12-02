package ru.mylife54.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mylife54.models.Role;
import ru.mylife54.repositories.RoleRepo;
import ru.mylife54.services.RoleService;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role getRole(String name) {
        return roleRepo.findByName(name);
    }

    @Override
    public Collection<Role> getAll() {
        return roleRepo.findAll();
    }

    @Override
    public boolean deleteRole(Role role) {
        if (role != null) {
            roleRepo.delete(role);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Role add(Role role) {
        if (role != null) {
            roleRepo.saveAndFlush(role);
        }
        return roleRepo.findByName(role.getName());
    }

    @Override
    public Role update(Role role) {
        if (role != null) {
            roleRepo.saveAndFlush(role);
        }
        return roleRepo.findByName(role.getName());
    }
}

package ru.mylife54.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mylife54.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

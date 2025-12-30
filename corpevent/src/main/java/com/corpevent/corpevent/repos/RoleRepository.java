package com.corpevent.corpevent.repos;


import com.corpevent.corpevent.enums.RoleName;
import com.corpevent.corpevent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(RoleName name);
}

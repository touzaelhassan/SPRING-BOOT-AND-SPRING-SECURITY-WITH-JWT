package com.application.services.specifications;

import com.application.entities.Role;

import java.util.List;

public interface RoleServiceSpecification {

    Role saveRole(Role role);
    List<Role> getRoles();

}

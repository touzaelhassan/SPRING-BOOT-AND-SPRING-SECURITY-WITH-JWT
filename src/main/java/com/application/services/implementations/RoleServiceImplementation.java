package com.application.services.implementations;

import com.application.entities.Role;
import com.application.repositories.RoleRepository;
import com.application.services.specifications.RoleServiceSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("roleServiceBean")
@AllArgsConstructor
@Transactional
public class RoleServiceImplementation implements RoleServiceSpecification {

    private final RoleRepository roleRepositoryBean;

    @Override
    public Role saveRole(Role role) { return roleRepositoryBean.save(role); }

}

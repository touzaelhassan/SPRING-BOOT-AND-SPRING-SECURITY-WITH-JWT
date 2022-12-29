package com.application.services.implementations;

import com.application.entities.Role;
import com.application.repositories.RoleRepository;
import com.application.services.specifications.RoleServiceSpecification;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("roleServiceBean")
@Transactional
public class RoleServiceImplementation implements RoleServiceSpecification {

    private final RoleRepository roleRepositoryBean;

    @Autowired
    public RoleServiceImplementation(RoleRepository roleRepositoryBean) { this.roleRepositoryBean = roleRepositoryBean; }
    @Override
    public Role saveRole(Role role) { return roleRepositoryBean.save(role); }
    @Override
    public List<Role> getRoles() { return roleRepositoryBean.findAll(); }

}

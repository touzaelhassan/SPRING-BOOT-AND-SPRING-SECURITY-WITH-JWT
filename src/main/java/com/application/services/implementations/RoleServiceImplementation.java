package com.application.services.implementations;

import com.application.entities.Role;
import com.application.repositories.RoleRepository;
import com.application.services.specifications.RoleServiceSpecification;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("roleServiceBean")
@RequiredArgsConstructor
@Transactional
public class RoleServiceImplementation implements RoleServiceSpecification {

    private final RoleRepository roleRepositoryBean;

    @Override
    public Role saveRole(Role role) { return roleRepositoryBean.save(role); }

    @Override
    public List<Role> getRoles() { return roleRepositoryBean.findAll(); }

}

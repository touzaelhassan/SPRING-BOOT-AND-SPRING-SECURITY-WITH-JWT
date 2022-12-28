package com.application.services.implementations;

import com.application.entities.Role;
import com.application.entities.User;
import com.application.repositories.RoleRepository;
import com.application.repositories.UserRepository;
import com.application.services.specifications.UserServiceSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userServiceBean")
@AllArgsConstructor
@Transactional
public class UserServiceImplementation implements UserServiceSpecification {

    private final UserRepository userRepositoryBean;
    private final RoleRepository roleRepositoryBean;

    @Override
    public User saveUser(User user) { return userRepositoryBean.save(user); }

    @Override
    public User getUserByUsername(String username) { return userRepositoryBean.findByUsername(username); }

    @Override
    public List<User> getUsers() { return userRepositoryBean.findAll(); }

    @Override
    public void addRoleToUser(String username, String roleName) {

        User user = userRepositoryBean.findByUsername(username);
        Role role = roleRepositoryBean.findByName(roleName);
        user.getRoles().add(role);

    }

}

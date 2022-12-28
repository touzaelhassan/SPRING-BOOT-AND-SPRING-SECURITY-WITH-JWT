package com.application.services.implementations;

import com.application.entities.Role;
import com.application.entities.User;
import com.application.repositories.RoleRepository;
import com.application.repositories.UserRepository;
import com.application.services.specifications.UserServiceSpecification;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userServiceBean")
@RequiredArgsConstructor
@Transactional
public class UserServiceImplementation implements UserServiceSpecification, UserDetailsService {

    private final UserRepository userRepositoryBean;
    private final RoleRepository roleRepositoryBean;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        User user = userRepositoryBean.findByUsername(username);
        if(user == null) { throw new UsernameNotFoundException("User not found in the database !!."); }
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }

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

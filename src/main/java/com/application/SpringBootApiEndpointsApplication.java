package com.application;

import com.application.entities.Role;
import com.application.entities.User;
import com.application.services.specifications.RoleServiceSpecification;
import com.application.services.specifications.UserServiceSpecification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringBootApiEndpointsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApiEndpointsApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserServiceSpecification userServiceBean, RoleServiceSpecification roleServiceBean){

        return args -> {

            roleServiceBean.saveRole(new Role(null, "USER"));
            roleServiceBean.saveRole(new Role(null, "MANAGER"));
            roleServiceBean.saveRole(new Role(null, "ADMIN"));

            userServiceBean.saveUser(new User(null, "user1","username1","user1@gmail.com","123456",new ArrayList<>()));
            userServiceBean.saveUser(new User(null, "user2","username2","user2@gmail.com","123456",new ArrayList<>()));
            userServiceBean.saveUser(new User(null, "user3","username3","user3@gmail.com","123456",new ArrayList<>()));

            userServiceBean.addRoleToUser("username1","ADMIN");
            userServiceBean.addRoleToUser("username1","MANAGER");
            userServiceBean.addRoleToUser("username1","USER");

            userServiceBean.addRoleToUser("username2","MANAGER");
            userServiceBean.addRoleToUser("username2","USER");

            userServiceBean.addRoleToUser("username3","USER");

        };

    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

}

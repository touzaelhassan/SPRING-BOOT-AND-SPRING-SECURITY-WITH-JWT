package com.application;

import com.application.entities.Product;
import com.application.entities.Role;
import com.application.entities.User;
import com.application.services.specifications.ProductServiceSpecification;
import com.application.services.specifications.RoleServiceSpecification;
import com.application.services.specifications.UserServiceSpecification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

    @Bean
    CommandLineRunner run(
            UserServiceSpecification userServiceBean,
            RoleServiceSpecification roleServiceBean,
            ProductServiceSpecification productServiceBean
            ){

        return args -> {

            roleServiceBean.saveRole(new Role( "USER"));
            roleServiceBean.saveRole(new Role("MANAGER"));
            roleServiceBean.saveRole(new Role("ADMIN"));

            userServiceBean.addUser(new User( "admin","admin","admin@gmail.com","123456",new ArrayList<>()));
            userServiceBean.addUser(new User("user1","username1","user1@gmail.com","123456",new ArrayList<>()));
            userServiceBean.addUser(new User("user2","username2","user2@gmail.com","123456",new ArrayList<>()));
            userServiceBean.addUser(new User("user3","username3","user3@gmail.com","123456",new ArrayList<>()));

            userServiceBean.addRoleToUser("admin","ADMIN");
            userServiceBean.addRoleToUser("username1","MANAGER");
            userServiceBean.addRoleToUser("username2","USER");
            userServiceBean.addRoleToUser("username3","USER");

            productServiceBean.addProduct(new Product("Product1","454768","The description for product 1", 55.99F,50));
            productServiceBean.addProduct(new Product("Product2","454768","The description for product 1", 35.45F,75));
            productServiceBean.addProduct(new Product("Product3","454768","The description for product 1", 20.10F,100));

        };

    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

}

package com.application.controllers.api;

import com.application.entities.User;
import com.application.services.specifications.UserServiceSpecification;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserServiceSpecification userServiceBean;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){ return ResponseEntity.ok().body(userServiceBean.getUsers()); }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){ return ResponseEntity.ok().body(userServiceBean.saveUser(user)); }

    @PostMapping("/user/add-role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm  ){

        userServiceBean.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();

    }

}

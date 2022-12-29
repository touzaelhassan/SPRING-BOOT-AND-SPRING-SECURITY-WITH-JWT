package com.application.controllers.api;

import com.application.entities.Role;
import com.application.services.specifications.RoleServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final RoleServiceSpecification roleServiceBean;

    @Autowired
    public RoleController(RoleServiceSpecification roleServiceBean) { this.roleServiceBean = roleServiceBean; }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){ return ResponseEntity.ok().body(roleServiceBean.getRoles()); }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){ return ResponseEntity.ok().body(roleServiceBean.saveRole(role)); }

}

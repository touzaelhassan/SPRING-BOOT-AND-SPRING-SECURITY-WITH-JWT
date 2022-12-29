package com.application.controllers.api;

import com.application.dto.RoleToUserForm;
import com.application.entities.Role;
import com.application.entities.User;
import com.application.services.specifications.UserServiceSpecification;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceSpecification userServiceBean;

    @Autowired
    public UserController(UserServiceSpecification userServiceBean) { this.userServiceBean = userServiceBean; }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){ return ResponseEntity.ok().body(userServiceBean.getUsers()); }
    @PostMapping("/user/add")
    public ResponseEntity<User> saveUser(@RequestBody User user){ return ResponseEntity.ok().body(userServiceBean.addUser(user)); }
    @PostMapping("/user/add-role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm  ){
        userServiceBean.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){

            String refreshToken = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refreshToken);
            String username = decodedJWT.getSubject();
            User user = userServiceBean.getUserByUsername(username);

            String accessToken = JWT
                    .create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withIssuer(request.getRequestURI().toString())
                    .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", refreshToken);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);

        }else{

            throw new RuntimeException("Refresh token is missing");

        }

    }

}

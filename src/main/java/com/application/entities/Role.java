package com.application.entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Role() { }
    public Role(String name) { this.name = name; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name;}
    public Long getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}

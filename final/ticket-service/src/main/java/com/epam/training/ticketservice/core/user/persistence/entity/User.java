package com.epam.training.ticketservice.core.user.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(Integer id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username)
                && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username
                + '\'' + ", password='" + password + '\'' + ", role=" + role + '}';
    }

    public static enum Role {

        ADMIN, USER

    }

}

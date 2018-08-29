package com.authorization.entities;

import com.authorization.configs.OAuth2Config;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "auth_users_roles")
public class UserRoles {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private String role;
}

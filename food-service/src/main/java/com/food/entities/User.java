package com.food.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client")
public class User {
    @Column(name = "client_name")
    private String name;

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_password")
    private String password;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ClientOrder> orders;
}

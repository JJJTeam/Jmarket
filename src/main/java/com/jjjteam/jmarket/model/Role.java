package com.jjjteam.jmarket.model;


import com.jjjteam.jmarket.constant.ERole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true)
    @Getter
    @Setter
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }

}
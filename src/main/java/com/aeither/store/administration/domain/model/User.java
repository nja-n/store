package com.aeither.store.administration.domain.model;

import com.aeither.store.common.domain.AuditableEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String status; // ACTIVE, DELETED

    @ManyToOne
    private Company company;

    @ManyToOne
    private Store store;
}

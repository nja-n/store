package com.aeither.store.administration.domain.model;

import com.aeither.store.common.domain.AuditableEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status; // ACTIVE, DELETED

    private String gstin;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String logo;
}

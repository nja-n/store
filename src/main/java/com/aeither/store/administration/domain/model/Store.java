package com.aeither.store.administration.domain.model;

import com.aeither.store.common.domain.AuditableEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
public class Store extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String gstNumber;

    private String name;
    private String location;
    private String address;
    private String contactPerson;
    private String mobileNumber;
    private String email;

    private Long companyId;
    private boolean loginEnabled;

    private String status;
}

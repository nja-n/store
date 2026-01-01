package com.aeither.store.stock.domain.model;

import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.assests.domain.model.Asset;
import com.aeither.store.common.domain.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stocks")
@Getter
@Setter
@NoArgsConstructor
public class Stock extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Asset asset;

    @ManyToOne
    private Company company;

    private Integer quantity;

    private String status; // ACTIVE, OUT_OF_STOCK
}
